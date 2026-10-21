package dev.omalauncher

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.IBinder
import dev.omalauncher.linux.GuestSession
import dev.omalauncher.linux.LinuxState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.File

/**
 * Keeps the process (and with it proot and the Wayland server) in the
 * foreground while Linux is installed or installing; never without Linux.
 * The notification shows the install's progress.
 */
class LinuxService : Service() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private var watch: Job? = null

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int,
    ): Int {
        val nm = getSystemService(NotificationManager::class.java)
        nm.createNotificationChannel(NotificationChannel(CHANNEL, "Linux session", NotificationManager.IMPORTANCE_LOW))
        val state = (application as App).runtime.bootstrap.state
        val foreground =
            runCatching {
                startForeground(1, notification(state.value), ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE)
            }
        if (foreground.isFailure) {
            // A sticky restart from the background that Android 12+ didn't allow in the foreground.
            stopSelf()
            return START_NOT_STICKY
        }
        if (watch == null) {
            watch =
                scope.launch {
                    state.map(::text).distinctUntilChanged().collect { nm.notify(1, build(it)) }
                }
        }
        // With ~/.config/omalauncher/keep-running, Android restarts this service after it kills the
        // process (low memory, a swipe from Recents), and the restart brings Linux back without the
        // launcher being opened: the guest's session (GuestSession) then restarts its services.
        // Android 12+ lets a restart start in the foreground when the app is exempt from battery
        // optimization; if it refuses, the restart gives up quietly as before.
        // Without the file Linux only comes back with the launcher (Runtime.start).
        val runtime = (application as App).runtime
        if (!File(runtime.proot.launcherConfig, GuestSession.KEEP_RUNNING).exists()) return START_NOT_STICKY
        runtime.start()
        return START_STICKY
    }

    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
    }

    private fun notification(state: LinuxState) = build(text(state))

    private fun text(state: LinuxState): String =
        when (state) {
            is LinuxState.Installing -> "Installing Linux" + (state.progress?.let { " · ${(it * 100).toInt()}%" } ?: "") + " · ${state.stage.label}"
            is LinuxState.Failed -> "Linux setup stopped · open settings › linux to retry"
            LinuxState.Removing -> "Removing Linux"
            else -> "Linux session running"
        }

    private fun build(text: String): Notification =
        Notification
            .Builder(this, CHANNEL)
            .setContentTitle(text)
            .setSmallIcon(R.drawable.ic_notification)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .build()

    companion object {
        private const val CHANNEL = "session"
    }
}
