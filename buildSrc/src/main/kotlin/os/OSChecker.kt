object OSChecker {
    private val osNameLowercased = System.getProperty("os.name").toLowerCase()
    private val osArchLowercased = System.getProperty("os.arch").toLowerCase()

    val os: OS = if (osNameLowercased.contains("nix") || osNameLowercased.contains("nux")) {
        OS.Linux
    } else if (osNameLowercased.contains("win")) {
        OS.Windows
    } else if (osNameLowercased.contains("mac")) {
        OS.`Mac OS X`
    } else {
        OS.Unknown
    }

    val arch: Architecture = if (osArchLowercased.contains("arm")) {
        Architecture.arm
    } else if (osArchLowercased.contains("aarch64")) {
        Architecture.arm64
    } else if (osArchLowercased.contains("86") || osArchLowercased.contains("amd")) {
        if (osArchLowercased.contains("64")) Architecture.x86_64 else Architecture.x86
    } else {
        Architecture.Unknown
    }

    val platform: Platform = when (os) {
        OS.Linux -> when (arch) {
            Architecture.x86 -> Platform.`Linux x86`
            Architecture.x86_64 -> Platform.`Linux x86_64`
            Architecture.arm -> Platform.`Linux arm`
            Architecture.arm64 -> Platform.`Linux arm64`
            Architecture.Unknown -> Platform.Unknown
        }
        OS.Windows -> when (arch) {
            Architecture.x86 -> Platform.`Windows x86`
            Architecture.x86_64 -> Platform.`Windows x86_64`
            Architecture.arm -> Platform.Unknown
            Architecture.arm64 -> Platform.`Windows arm64`
            Architecture.Unknown -> Platform.Unknown
        }
        OS.`Mac OS X` -> when (arch) {
            Architecture.x86 -> Platform.`Mac OS X x86`
            Architecture.x86_64 -> Platform.`Mac OS X x86_64`
            Architecture.arm -> Platform.Unknown
            Architecture.arm64 -> Platform.`Mac OS X arm64`
            Architecture.Unknown -> Platform.Unknown
        }
        OS.Unknown -> Platform.Unknown
    }
}

enum class OS {
    Windows, Linux, `Mac OS X`, Unknown
}

enum class Architecture {
    x86, x86_64, arm, arm64, Unknown
}

enum class Platform {
    `Windows x86` {
        override fun toString(): String = "windows_32"
    },
    `Windows x86_64` {
        override fun toString(): String = "windows_64"
    },
    `Windows arm64` {
        override fun toString(): String = "windows_arm64"
    },
    `Linux x86` {
        override fun toString(): String = "linux_32"
    },
    `Linux x86_64` {
        override fun toString(): String = "linux_64"
    },
    `Linux arm` {
        override fun toString(): String = "linux_arm"
    },
    `Linux arm64` {
        override fun toString(): String = "linux_arm64"
    },
    `Mac OS X x86` {
        override fun toString(): String = "osx_32"
    },
    `Mac OS X x86_64` {
        override fun toString(): String = "osx_64"
    },
    `Mac OS X arm64` {
        override fun toString(): String = "osx_arm64"
    },
    Unknown {
        override fun toString(): String = throw RuntimeException("Platform (OS + Architecture) is not supported")
    };
}
