package dev.omalauncher

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dev.omalauncher.linux.GuestSession
import java.io.File

/**
 * Starts Linux after the phone boots or the app is updated, only when the user
 * asked for it with `~/.config/omalauncher/keep-running`. A foreground service
 * may be started from these broadcasts on Android 12+.
 */
class BootReceiver : BroadcastReceiver() {
    override fun onReceive(
        context: Context,
        intent: Intent,
    ) {
        if (intent.action !in ACTIONS) return
        val runtime = (context.applicationContext as App).runtime
        if (!File(runtime.proot.launcherConfig, GuestSession.KEEP_RUNNING).exists()) return
        runtime.start()
    }

    private companion object {
        val ACTIONS = setOf(Intent.ACTION_BOOT_COMPLETED, Intent.ACTION_MY_PACKAGE_REPLACED)
    }
}
