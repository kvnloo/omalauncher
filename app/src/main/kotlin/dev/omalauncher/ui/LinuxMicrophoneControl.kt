package dev.omalauncher.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import dev.omalauncher.Runtime

/** Explicitly shares raw microphone audio with Linux; this is not speech-to-text dictation. */
@Composable
fun LinuxMicrophoneControl(
    runtime: Runtime,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val state by runtime.microphone.state.collectAsState()
    var permissionError by remember { mutableStateOf<String?>(null) }
    val permission = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        permissionError = if (granted) null else "Allow Omalauncher's microphone permission in Android settings."
        if (granted) runtime.setMicrophone(true)
    }
    Column(modifier.widthIn(max = 240.dp)) {
        BasicText(
            when {
                state.enabled -> "mic on · stop"
                state.starting -> "mic starting · cancel"
                else -> "share phone mic"
            },
            modifier = Modifier
                .background(Palette.overlay, LauncherStyle.shape(12.dp))
                .clickable(role = Role.Button) {
                    permissionError = null
                    when {
                        state.enabled || state.starting -> runtime.setMicrophone(false)
                        context.checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED -> runtime.setMicrophone(true)
                        else -> permission.launch(Manifest.permission.RECORD_AUDIO)
                    }
                }
                .padding(horizontal = 12.dp, vertical = 10.dp),
            style = Mono.copy(color = if (state.enabled) Palette.red else Palette.foreground, fontSize = TextSize.Small),
        )
        val error = permissionError ?: state.error
        if (error != null) {
            BasicText(error, style = Mono.copy(color = Palette.red, fontSize = TextSize.Small), maxLines = 3)
        }
    }
}
