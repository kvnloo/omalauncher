package dev.omalauncher.linux

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.media.audiofx.AcousticEchoCanceler
import android.media.audiofx.AudioEffect
import android.os.SystemClock
import android.system.ErrnoException
import android.system.Os
import android.system.OsConstants
import android.util.Log
import java.io.File
import java.io.FileDescriptor
import java.nio.ByteBuffer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.concurrent.thread

private const val MIC_TAG = "microphone"
private const val MIC_RATE = 48000
private const val MIC_FRAME_BYTES = 2 // s16le mono on Android's little-endian ABIs
private const val MIC_CHUNK_MS = 20L
private const val MIC_CHUNK_BYTES = MIC_RATE * MIC_FRAME_BYTES / 50
private const val PIPE_RETRY_MS = 100L

data class MicrophoneState(
    val enabled: Boolean = false,
    val starting: Boolean = false,
    val error: String? = null,
)

/** Opt-in foreground capture. PulseAudio owns the FIFO; this class only opens its writer. */
class MicrophoneBridge(
    context: Context,
    proot: Proot,
) {
    private val context = context.applicationContext
    private val fifo = File(proot.runtimeDir, "android-mic.fifo")

    val sourceConfig: String =
        """
        load-module module-pipe-source file=${Proot.GUEST_RUNTIME_DIR}/${fifo.name} format=s16le rate=$MIC_RATE channels=1 source_name=$SOURCE_NAME source_properties="device.description=Android-Microphone device.class=sound"
        set-default-source $SOURCE_NAME
        """.trimIndent() + "\n"

    private val _state = MutableStateFlow(MicrophoneState())
    val state: StateFlow<MicrophoneState> = _state.asStateFlow()

    private val lock = Object()
    private var requested = false
    private var generation = 0L
    private var worker: Thread? = null

    /** The caller obtains permission and ensures a launcher screen is visible before calling this. */
    fun start() {
        synchronized(lock) {
            if (requested) return
            if (context.checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                _state.value = MicrophoneState(error = "Microphone permission is not granted")
                return
            }
            requested = true
            generation++
            _state.value = MicrophoneState(starting = true)
            if (worker == null) {
                try {
                    worker = thread(name = "android-microphone", isDaemon = true, start = false) { runWorker() }
                    worker!!.start()
                } catch (e: Exception) {
                    worker = null
                    requested = false
                    _state.value = MicrophoneState(error = "Cannot start microphone: ${e.message ?: e.javaClass.simpleName}")
                }
            }
            lock.notifyAll()
        }
    }

    /** Never joins or calls an audio/pipe API on the caller's thread. Cleanup belongs to the worker. */
    fun stop() {
        synchronized(lock) {
            requested = false
            generation++
            _state.value = MicrophoneState()
            lock.notifyAll()
        }
    }

    private fun runWorker() {
        while (true) {
            val token = synchronized(lock) {
                if (!requested) {
                    worker = null
                    return
                }
                generation
            }
            capture(token)
        }
    }

    private fun isCurrent(token: Long): Boolean = synchronized(lock) { requested && generation == token }

    private fun publish(token: Long, value: MicrophoneState) {
        synchronized(lock) {
            if (requested && generation == token) _state.value = value
        }
    }

    /** Timed waits are interruptible by stop/start without polling or a missed wakeup. */
    private fun pause(token: Long, millis: Long) {
        synchronized(lock) {
            if (requested && generation == token) lock.wait(millis.coerceAtLeast(1L))
        }
    }

    private fun openPipe(token: Long): FileDescriptor? {
        val waiting = MicrophoneState(starting = true, error = "Waiting for the PulseAudio microphone source")
        while (isCurrent(token)) {
            try {
                val fd = Os.open(fifo.absolutePath, OsConstants.O_WRONLY or OsConstants.O_NONBLOCK or OsConstants.O_CLOEXEC or OsConstants.O_NOFOLLOW, 0)
                try {
                    check(OsConstants.S_ISFIFO(Os.fstat(fd).st_mode)) { "Android microphone path is not a FIFO" }
                    return fd
                } catch (e: Exception) {
                    closePipe(fd)
                    throw e
                }
            } catch (e: ErrnoException) {
                when (e.errno) {
                    OsConstants.ENOENT, OsConstants.ENXIO, OsConstants.EAGAIN -> {
                        publish(token, waiting)
                    }
                    OsConstants.EINTR -> Unit
                    else -> throw e
                }
                pause(token, PIPE_RETRY_MS)
            }
        }
        return null
    }

    private fun capture(token: Long) {
        var pipe: FileDescriptor? = null
        var recorder: AudioRecord? = null
        var echoCanceler: AcousticEchoCanceler? = null
        try {
            // Startup of the guest daemon can lag the UI. Do not hold a microphone while waiting.
            pipe = openPipe(token) ?: return
            if (!isCurrent(token)) return
            check(context.checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                "Microphone permission is not granted"
            }
            val minBuffer = AudioRecord.getMinBufferSize(MIC_RATE, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT)
            check(minBuffer > 0) { "Android does not support 48000 Hz mono PCM16 microphone capture ($minBuffer)" }
            recorder = AudioRecord.Builder()
                .setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION)
                .setAudioFormat(
                    AudioFormat.Builder()
                        .setSampleRate(MIC_RATE)
                        .setChannelMask(AudioFormat.CHANNEL_IN_MONO)
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .build(),
                )
                .setBufferSizeInBytes(maxOf(minBuffer, MIC_CHUNK_BYTES * 2))
                .build()
            check(recorder.state == AudioRecord.STATE_INITIALIZED) { "Android microphone could not initialize" }

            // VOICE_COMMUNICATION already enables device voice processing; explicit AEC is optional.
            try {
                if (AcousticEchoCanceler.isAvailable()) {
                    echoCanceler = AcousticEchoCanceler.create(recorder.audioSessionId)
                    if (echoCanceler != null && echoCanceler.setEnabled(true) != AudioEffect.SUCCESS) {
                        echoCanceler.release()
                        echoCanceler = null
                        Log.w(MIC_TAG, "Microphone echo cancellation could not be enabled")
                    }
                }
            } catch (e: Exception) {
                runCatching { echoCanceler?.release() }
                echoCanceler = null
                Log.w(MIC_TAG, "Optional microphone echo cancellation is unavailable", e)
            }
            if (!isCurrent(token)) return
            recorder.startRecording()
            check(recorder.recordingState == AudioRecord.RECORDSTATE_RECORDING) { "Android microphone could not start recording" }
            publish(token, MicrophoneState(enabled = true))

            // No heap copies or pending-write queue. Each read is at most 20 ms; full pipes drop it.
            val buffer = ByteBuffer.allocateDirect(MIC_CHUNK_BYTES)
            while (isCurrent(token)) {
                val started = SystemClock.uptimeMillis()
                buffer.clear()
                val count = recorder.read(buffer, MIC_CHUNK_BYTES, AudioRecord.READ_NON_BLOCKING)
                check(count >= 0) { "Android microphone read failed ($count)" }
                check(count % MIC_FRAME_BYTES == 0) { "Android microphone returned an incomplete PCM16 sample" }
                if (count > 0 && isCurrent(token)) {
                    buffer.position(0)
                    buffer.limit(count)
                    try {
                        // <= PIPE_BUF: a nonblocking FIFO write is all-or-nothing, never a partial sample.
                        check(Os.write(pipe, buffer) == count) { "Android microphone FIFO write was incomplete" }
                    } catch (e: ErrnoException) {
                        when (e.errno) {
                            // Retry neither the chunk nor a growing backlog of stale audio.
                            OsConstants.EAGAIN, OsConstants.EINTR -> Unit
                            else -> throw e
                        }
                    }
                }
                val capturedMs = if (count == 0) 2L else (count.toLong() * 1000 / (MIC_RATE * MIC_FRAME_BYTES)).coerceIn(1L, MIC_CHUNK_MS)
                pause(token, capturedMs - (SystemClock.uptimeMillis() - started))
            }
        } catch (e: Exception) {
            synchronized(lock) {
                if (requested && generation == token) {
                    requested = false
                    _state.value = MicrophoneState(error = "Microphone capture failed: ${e.message ?: e.javaClass.simpleName}")
                    Log.w(MIC_TAG, "Microphone capture failed", e)
                }
            }
        } finally {
            // A new generation cannot allocate its recorder until these resources have been released.
            runCatching { recorder?.stop() }
            runCatching { echoCanceler?.release() }.onFailure { Log.w(MIC_TAG, "Microphone echo cancellation release failed", it) }
            runCatching { recorder?.release() }.onFailure { Log.w(MIC_TAG, "Microphone release failed", it) }
            pipe?.let { closePipe(it) }
        }
    }

    private fun closePipe(fd: FileDescriptor) {
        runCatching { Os.close(fd) }.onFailure { Log.w(MIC_TAG, "Microphone FIFO close failed", it) }
    }

    companion object {
        const val SOURCE_NAME = "android_mic"
    }
}
