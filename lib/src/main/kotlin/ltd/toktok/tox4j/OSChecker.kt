package ltd.toktok.tox4j

object OSChecker {
    private val osNameLowercased = System.getProperty("os.name").lowercase()
    private val osArchLowercased = System.getProperty("os.arch").lowercase()

    val os: OS = if (osNameLowercased.contains("nix") || osNameLowercased.contains("nux")) {
        OS.LINUX
    } else if (osNameLowercased.contains("win")) {
        OS.WINDOWS
    } else if (osNameLowercased.contains("mac")) {
        OS.OSX
    } else {
        OS.UNKOWN
    }

    val arch: Architecture = if (osArchLowercased.contains("arm")) {
        Architecture.ARM
    } else if (osArchLowercased.contains("aarch64")) {
        Architecture.ARM64
    } else if (osArchLowercased.contains("86") || osArchLowercased.contains("amd")) {
        if (osArchLowercased.contains("64")) Architecture.X86_64 else Architecture.X86
    } else {
        Architecture.UNKOWN
    }

    val platform: Platform = when (os) {
        OS.LINUX -> when (arch) {
            Architecture.X86 -> Platform.LINUX_X86
            Architecture.X86_64 -> Platform.LINUX_X86_64
            Architecture.ARM -> Platform.LINUX_ARM
            Architecture.ARM64 -> Platform.LINUX_ARM64
            Architecture.UNKOWN -> Platform.UNKNOWN
        }
        OS.WINDOWS -> when (arch) {
            Architecture.X86 -> Platform.WINDOWS_X86
            Architecture.X86_64 -> Platform.WINDOWS_X86_64
            Architecture.ARM -> Platform.UNKNOWN
            Architecture.ARM64 -> Platform.WINDOWS_ARM64
            Architecture.UNKOWN -> Platform.UNKNOWN
        }
        OS.OSX -> when (arch) {
            Architecture.X86 -> Platform.OSX_X86
            Architecture.X86_64 -> Platform.OSX_X86_64
            Architecture.ARM -> Platform.UNKNOWN
            Architecture.ARM64 -> Platform.OSX_ARM64
            Architecture.UNKOWN -> Platform.UNKNOWN
        }
        OS.UNKOWN -> Platform.UNKNOWN
    }
}

enum class OS {
    WINDOWS, LINUX, OSX, UNKOWN
}

enum class Architecture {
    X86, X86_64, ARM, ARM64, UNKOWN
}

enum class Platform {
    WINDOWS_X86 {
        override fun toString(): String = "windows_32"
    },
    WINDOWS_X86_64 {
        override fun toString(): String = "windows_64"
    },
    WINDOWS_ARM64 {
        override fun toString(): String = "windows_arm64"
    },
    LINUX_X86 {
        override fun toString(): String = "linux_32"
    },
    LINUX_X86_64 {
        override fun toString(): String = "linux_64"
    },
    LINUX_ARM {
        override fun toString(): String = "linux_arm"
    },
    LINUX_ARM64 {
        override fun toString(): String = "linux_arm64"
    },
    OSX_X86 {
        override fun toString(): String = "osx_32"
    },
    OSX_X86_64 {
        override fun toString(): String = "osx_64"
    },
    OSX_ARM64 {
        override fun toString(): String = "osx_arm64"
    },
    UNKNOWN {
        override fun toString(): String = throw RuntimeException("Platform (OS + Architecture) is not supported")
    };
}
