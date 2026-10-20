package dev.omalauncher

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class WindowTasksTest {
    @Test fun matchesOnlyTheExactWindowDocumentUri() {
        assertTrue(isWindowTaskUri("omarchy://window/session-1", "session-1"))
        assertFalse(isWindowTaskUri("omarchy://window/session-10", "session-1"))
        assertFalse(isWindowTaskUri("omarchy://window/old-session-1", "session-1"))
        assertFalse(isWindowTaskUri("omarchy://different/session-1", "session-1"))
        assertFalse(isWindowTaskUri("https://window/session-1", "session-1"))
        assertFalse(isWindowTaskUri(null, "session-1"))
    }
}
