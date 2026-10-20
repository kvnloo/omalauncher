package dev.omalauncher.ui

import org.junit.Assert.assertEquals
import org.junit.Test

class DrawerFocusTest {
    @Test fun tapOnlyDrawerDoesNotStealSearchFocus() {
        assertEquals(DrawerFocusAction.None, drawerFocusAction(shown = true, autoKeyboard = false, listening = false, focused = false))
        // An explicit tap on the field may already have focused it; opening the drawer must not undo that.
        assertEquals(DrawerFocusAction.None, drawerFocusAction(shown = true, autoKeyboard = false, listening = false, focused = true))
    }

    @Test fun defaultDrawerStillRequestsKeyboardUnlessDictating() {
        assertEquals(DrawerFocusAction.Focus, drawerFocusAction(shown = true, autoKeyboard = true, listening = false, focused = false))
        assertEquals(DrawerFocusAction.None, drawerFocusAction(shown = true, autoKeyboard = true, listening = true, focused = false))
    }

    @Test fun closingDrawerReleasesSearchFocusRegardlessOfPreference() {
        for (autoKeyboard in listOf(false, true)) {
            assertEquals(DrawerFocusAction.Release, drawerFocusAction(shown = false, autoKeyboard = autoKeyboard, listening = false, focused = true))
            assertEquals(DrawerFocusAction.None, drawerFocusAction(shown = false, autoKeyboard = autoKeyboard, listening = false, focused = false))
        }
    }
}
