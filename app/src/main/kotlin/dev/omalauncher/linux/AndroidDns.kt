package dev.omalauncher.linux

import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.util.Log
import dev.omalauncher.upgrade.writeAtomically
import java.io.File
import java.net.InetAddress

/**
 * The guest uses glibc /etc/resolv.conf, not Android's netd resolver. Publish
 * DNS from the active default network (often a VPN), and replace it atomically
 * when Android reports changes. All guest processes share the rootfs file.
 *
 * This does not implement Android's encrypted Private DNS transport: glibc
 * cannot negotiate DNS-over-TLS from resolv.conf alone.
 */
class AndroidDns(context: Context, private val resolvConf: File) {
    private val manager = context.getSystemService(ConnectivityManager::class.java)
    private var registered = false

    private val callback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) = refresh()
        override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) = refresh()
        override fun onLost(network: Network) = refresh()
    }

    @Synchronized
    fun start() {
        if (!registered && manager != null) {
            runCatching { manager.registerDefaultNetworkCallback(callback) }
                .onSuccess { registered = true }
                .onFailure { Log.w("dns", "cannot monitor Android network", it) }
        }
        refresh()
    }

    @Synchronized
    fun stop() {
        if (!registered) return
        runCatching { manager?.unregisterNetworkCallback(callback) }
            .onFailure { Log.w("dns", "cannot stop network monitoring", it) }
        registered = false
    }

    @Synchronized
    fun refresh() {
        // The rootfs does not exist until the first Linux extraction finishes.
        if (resolvConf.parentFile?.isDirectory != true) return
        val link = runCatching {
            manager?.let { cm -> cm.activeNetwork?.let(cm::getLinkProperties) }
        }.onFailure { Log.w("dns", "cannot read Android DNS", it) }.getOrNull()
        val content = resolvConfText(link?.dnsServers.orEmpty(), link?.domains)
        runCatching {
            if (!resolvConf.isFile || resolvConf.readText() != content) {
                writeAtomically(resolvConf, content.toByteArray(Charsets.UTF_8))
            }
        }.onFailure { Log.w("dns", "cannot update guest resolver", it) }
    }
}

/**
 * An offline/default-less network uses loopback, not public DNS. Otherwise
 * VPN-only names could leak to a public resolver during network transitions.
 */
internal fun resolvConfText(servers: List<InetAddress>, domains: String?): String {
    val nameservers = servers.asSequence()
        .filterNot {
            it.isAnyLocalAddress || it.isLoopbackAddress || it.isLinkLocalAddress || it.isMulticastAddress
        }
        .mapNotNull { it.hostAddress?.substringBefore('%') }
        .distinct()
        .take(3)
        .toList()

    val suffixes = mutableListOf<String>()
    var budget = 240 // glibc limits the search directive's length.
    for (raw in domains.orEmpty().split(Regex("\\s+"))) {
        val domain = raw.trimEnd('.').lowercase()
        if (domain.isEmpty() || domain in suffixes || !validSearchDomain(domain)) continue
        if (suffixes.size == 6) break
        if (domain.length + 1 > budget) continue
        suffixes += domain
        budget -= domain.length + 1
    }

    return buildString {
        append("# Managed by Omalauncher: Android active-network DNS\n")
        for (ip in nameservers.ifEmpty { listOf("127.0.0.1") }) append("nameserver $ip\n")
        if (nameservers.isNotEmpty() && suffixes.isNotEmpty()) {
            append("search " + suffixes.joinToString(" ") + "\n")
        }
    }
}

private fun validSearchDomain(name: String): Boolean {
    if (name.length !in 1..253) return false
    return name.split('.').all { label ->
        label.length in 1..63 &&
            label.first().isLetterOrDigit() &&
            label.last().isLetterOrDigit() &&
            label.all { it in 'a'..'z' || it in '0'..'9' || it == '-' }
    }
}
