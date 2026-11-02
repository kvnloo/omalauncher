package dev.omalauncher.ui

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class HomePageDotsTest {
    @Test fun modeIdentifiersPersistAndUnknownValuesDefaultToSwiping() {
        assertEquals(PageDots.Swiping, PageDots.of(null))
        assertEquals(PageDots.Swiping, PageDots.of("unknown"))
        for (mode in PageDots.entries) {
            assertEquals(mode, PageDots.of(mode.id))
            assertEquals(mode, PageDots.of(mode.id.uppercase()))
        }
    }

    @Test fun roomIsReservedOnlyForMultipleUprightPagesWhenEnabled() {
        assertFalse(pageDotsAllowed(PageDots.Swiping, turned = false, pageCount = 1))
        assertFalse(pageDotsAllowed(PageDots.Always, turned = false, pageCount = 0))
        assertFalse(pageDotsAllowed(PageDots.Swiping, turned = true, pageCount = 3))
        assertFalse(pageDotsAllowed(PageDots.Always, turned = true, pageCount = 3))
        assertFalse(pageDotsAllowed(PageDots.Off, turned = false, pageCount = 3))
        assertTrue(pageDotsAllowed(PageDots.Swiping, turned = false, pageCount = 2))
        assertTrue(pageDotsAllowed(PageDots.Always, turned = false, pageCount = 4))
    }

    @Test fun visibilityRespectsModeFocusAndTimeout() {
        assertTrue(pageDotsVisible(PageDots.Always, focused = true, transient = false))
        assertTrue(pageDotsVisible(PageDots.Always, focused = false, transient = false))
        assertTrue(pageDotsVisible(PageDots.Swiping, focused = true, transient = true))
        assertFalse(pageDotsVisible(PageDots.Swiping, focused = true, transient = false))
        assertFalse(pageDotsVisible(PageDots.Swiping, focused = false, transient = true))
        assertFalse(pageDotsVisible(PageDots.Off, focused = true, transient = true))
    }
}
