package dev.omalauncher

/** Never match another document task merely because its last path segment looks like our handle. */
internal fun isWindowTaskUri(data: String?, handle: String): Boolean =
    data == "omarchy://window/$handle"
