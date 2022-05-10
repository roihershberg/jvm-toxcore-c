object OSChecker {
    private val osNameLowercased = System.getProperty("os.name").toLowerCase()

    val os: OS = if (osNameLowercased.contains("nix") || osNameLowercased.contains("nux")) {
        OS.Linux
    } else if (osNameLowercased.contains("win")) {
        OS.Windows
    } else if (osNameLowercased.contains("mac")) {
        OS.`Mac OS X`
    } else {
        OS.Unknown
    }
}

enum class OS {
    Windows, Linux, `Mac OS X`, Unknown
}
