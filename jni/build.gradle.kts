plugins {
    `cpp-library`
}

library {
    baseName.set("tox4j-jni")
}

tasks {
    val depsGitDir = "$rootDir/_git"
    val depsBuildDir = "$rootDir/_build"
    val depsInstallDir = "$rootDir/_install"
    val prefix = "$depsInstallDir/${OSChecker.platform}"

    withType<CppCompile>().configureEach {
        includes("$prefix/include")
        if (OSChecker.os == OS.`Mac OS X`) {
            includes("${org.gradle.internal.jvm.Jvm.current().javaHome}/include")
            includes("${org.gradle.internal.jvm.Jvm.current().javaHome}/include/darwin")
        } else if (OSChecker.os == OS.Linux) {
            includes("${org.gradle.internal.jvm.Jvm.current().javaHome}/include")
            includes("${org.gradle.internal.jvm.Jvm.current().javaHome}/include/linux")
        }
    }

    withType<LinkSharedLibrary>().configureEach {
        val libtoxcore = project.file("$prefix/lib")
            .listFiles { file: File -> file.name.contains("toxcore") }
            ?.firstOrNull() ?: throw RuntimeException("Can't find compiled toxcore library")

        lib(libtoxcore)
    }

    register<GitFetcher>("fetchGitToxcore") {
        from("https://github.com/TokTok/c-toxcore")
        to("$depsGitDir/toxcore")
        branch("v0.2.18")
        depth(1)
        recursive()
    }

    register<GitFetcher>("fetchGitLibsodium") {
        from("https://github.com/jedisct1/libsodium")
        to("$depsGitDir/libsodium")
        branch("1.0.18")
        depth(1)
    }

    register<GitFetcher>("fetchGitOpus") {
        from("https://github.com/xiph/opus")
        to("$depsGitDir/opus")
        branch("v1.3.1")
        depth(1)
    }

    register<GitFetcher>("fetchGitLibvpx") {
        from("https://github.com/webmproject/libvpx")
        to("$depsGitDir/libvpx")
        branch("v1.11.0")
        depth(1)
        withPatch("$rootDir/patches/libvpx.patch")
    }

    register("fetchGitDeps") {
        description = "Fetches the git repository of all the dependencies."
        dependsOn("fetchGitToxcore", "fetchGitLibsodium", "fetchGitOpus", "fetchGitLibvpx")
    }

    register<LibsodiumBuilder>("buildLibsodium") {
        dependsOn("fetchGitLibsodium")
        build("libsodium")
        from(depsGitDir)
        to(depsBuildDir)
        installTo(depsInstallDir)
        withConfiguration(LinuxHostConfiguration())
    }

    register<OpusBuilder>("buildOpus") {
        dependsOn("fetchGitOpus")
        build("opus")
        from(depsGitDir)
        to(depsBuildDir)
        installTo(depsInstallDir)
        withConfiguration(LinuxHostConfiguration())
    }

    register<LibvpxBuilder>("buildLibvpx") {
        dependsOn("fetchGitLibvpx")
        build("libvpx")
        from(depsGitDir)
        to(depsBuildDir)
        installTo(depsInstallDir)
        withConfiguration(LinuxHostConfiguration())
    }

    register<ToxcoreBuilder>("buildToxcore") {
        dependsOn("fetchGitToxcore", "buildLibsodium", "buildOpus", "buildLibvpx")
        build("toxcore")
        from(depsGitDir)
        to(depsBuildDir)
        installTo(depsInstallDir)
        withConfiguration(LinuxHostConfiguration())
    }
}
