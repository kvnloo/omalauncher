package dev.omalauncher.linux

import android.os.SystemClock
import android.util.Log
import java.io.File
import kotlin.concurrent.thread

private const val TAG = "session"

/** Restarts of a failing session script back off up to this. */
private const val MAX_BACKOFF_MS = 60_000L

/**
 * The user's own long-lived guest session: `~/.config/omalauncher/session`, if
 * it exists and is executable, runs as soon as Linux is up (screen on or off, no
 * window needed) in a proot of its own, and is started again when it exits, like
 * PulseAudio in [AudioBridge]. Everything it starts lives as long as it does, so
 * it is the place for services: a supervisor such as runit's `runsvdir`, sshd,
 * tailscaled. Without the file nothing runs.
 *
 * Guest processes otherwise live only as long as the window that started them
 * (proot's --kill-on-exit), so before this a service had to be started from a
 * terminal and died with it.
 */
class GuestSession(
    private val proot: Proot,
) {
    private val script get() = File(proot.launcherConfig, SCRIPT)

    @Volatile
    private var running = false
    private var started = false

    /** Starts the session (once per process), or resumes restarting it after [stop]. */
    fun start() {
        running = true
        if (started) return
        started = true
        thread(name = "guest-session", isDaemon = true) { supervise() }
    }

    /** Stops restarting the session; the caller ends the running one with the other guest processes. */
    fun stop() {
        running = false
    }

    private fun supervise() {
        var backoff = 1000L
        while (true) {
            while (!running || !script.canExecute()) Thread.sleep(5000)
            val began = SystemClock.elapsedRealtime()
            val code =
                runCatching {
                    // exec: the script is the proot's main command, so it and its children live until it exits.
                    proot.run("exec $GUEST_PATH") { Log.i(TAG, it) }
                }.getOrElse { -1 }
            if (SystemClock.elapsedRealtime() - began > MAX_BACKOFF_MS) backoff = 1000L
            Log.w(TAG, "$GUEST_PATH exited $code; restarting in ${backoff / 1000} s")
            Thread.sleep(backoff)
            backoff = (backoff * 2).coerceAtMost(MAX_BACKOFF_MS)
        }
    }

    companion object {
        const val SCRIPT = "session"
        const val GUEST_PATH = "${Proot.GUEST_HOME}/.config/omalauncher/$SCRIPT"

        /** `~/.config/omalauncher/keep-running`: restart Linux after Android kills the app, and start it at boot. */
        const val KEEP_RUNNING = "keep-running"
    }
}
