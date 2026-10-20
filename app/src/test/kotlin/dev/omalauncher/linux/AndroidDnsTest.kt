package dev.omalauncher.linux

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.net.InetAddress

class AndroidDnsTest {
    private fun ip(address: String) = InetAddress.getByName(address)

    @Test fun vpnDnsAndMagicDnsSearchDomainsArePreserved() {
        val text = resolvConfText(
            listOf(ip("100.100.100.100"), ip("1.1.1.1")),
            "tailnet.ts.net corp.example",
        )
        assertTrue(text.contains("nameserver 100.100.100.100\n"))
        assertTrue(text.contains("nameserver 1.1.1.1\n"))
        assertTrue(text.contains("search tailnet.ts.net corp.example\n"))
    }

    @Test fun missingNetworkNeverLeaksPrivateLookupsToPublicDns() {
        val text = resolvConfText(emptyList(), "private.ts.net")
        assertEquals(
            "# Managed by Omalauncher: Android active-network DNS\nnameserver 127.0.0.1\n",
            text,
        )
        assertFalse(text.contains("search private.ts.net"))
        assertFalse(text.contains("8.8.8.8"))
    }

    @Test fun ignoresUnusableServersDuplicatesAndExcessResolvers() {
        val text = resolvConfText(
            listOf(
                ip("127.0.0.1"), ip("::1"), ip("0.0.0.0"), ip("fe80::1"),
                ip("224.0.0.1"), ip("192.168.1.1"), ip("192.168.1.1"),
                ip("100.100.100.100"), ip("1.1.1.1"), ip("9.9.9.9"),
            ),
            null,
        )
        assertEquals(
            listOf("nameserver 192.168.1.1", "nameserver 100.100.100.100", "nameserver 1.1.1.1"),
            text.lines().filter { it.startsWith("nameserver ") },
        )
    }

    @Test fun rejectsInvalidSearchSuffixesWithoutInjectingResolverLines() {
        val text = resolvConfText(
            listOf(ip("100.100.100.100")),
            "tailnet.ts.net bad/name . bad..name -not-valid.com example.com.",
        )
        assertTrue(text.contains("search tailnet.ts.net example.com\n"))
        assertFalse(text.contains("bad/name"))
        assertFalse(text.contains("bad..name"))
        assertFalse(text.contains("-not-valid"))
    }
}
