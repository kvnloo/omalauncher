package dev.omalauncher.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

/** Room reserved between the home tiles and the recent-apps strip; no tiles or taps are displaced. */
internal val PageDotsHeight = 14.dp

internal fun pageDotsAllowed(mode: PageDots, turned: Boolean, pageCount: Int): Boolean =
    mode != PageDots.Off && !turned && pageCount > 1

internal fun pageDotsVisible(mode: PageDots, focused: Boolean, transient: Boolean): Boolean =
    mode == PageDots.Always || (mode == PageDots.Swiping && focused && transient)

/** Page position markers; swiping mode fades out after 1.5 seconds of rest or after the window loses focus. */
@Composable
internal fun HomePageDots(
    pager: PagerState,
    mode: PageDots,
    modifier: Modifier = Modifier,
) {
    val focused = LocalWindowInfo.current.isWindowFocused
    val scrolling = pager.isScrollInProgress
    var transient by remember { mutableStateOf(false) }
    LaunchedEffect(mode, focused, scrolling) {
        if (mode != PageDots.Swiping || !focused) {
            transient = false
        } else {
            transient = true
            if (!scrolling) {
                delay(1_500L)
                transient = false
            }
        }
    }
    val opacity by animateFloatAsState(
        targetValue = if (pageDotsVisible(mode, focused, transient)) 1f else 0f,
        label = "home page dots",
    )
    Row(
        modifier.height(PageDotsHeight).alpha(opacity),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(pager.pageCount) { index ->
            BasicText("•", style = Mono.copy(color = if (index == pager.currentPage) Palette.accent else Palette.muted))
        }
    }
}
