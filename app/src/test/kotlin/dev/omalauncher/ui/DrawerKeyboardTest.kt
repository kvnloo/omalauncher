package dev.omalauncher.ui

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DrawerKeyboardTest {
    @Test fun defaultOpensTheKeyboardWhenDrawerOpens() {
        assertTrue(shouldAutoFocusDrawerSearch(panelShown = true, listening = false, focusOnOpen = true))
    }

    @Test fun browsingModeLeavesKeyboardHiddenUntilTheBarIsTapped() {
        assertFalse(shouldAutoFocusDrawerSearch(panelShown = true, listening = false, focusOnOpen = false))
    }

    @Test fun speechNeverTriggersAnImplicitKeyboardRequest() {
        assertFalse(shouldAutoFocusDrawerSearch(panelShown = true, listening = true, focusOnOpen = true))
        assertFalse(shouldAutoFocusDrawerSearch(panelShown = true, listening = true, focusOnOpen = false))
    }

    @Test fun hiddenDrawerNeverRequestsKeyboard() {
        assertFalse(shouldAutoFocusDrawerSearch(panelShown = false, listening = false, focusOnOpen = true))
        assertFalse(shouldAutoFocusDrawerSearch(panelShown = false, listening = false, focusOnOpen = false))
    }
}
