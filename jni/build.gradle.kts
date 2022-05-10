plugins {
    `cpp-library`
}

tasks {
    val depsGitDir = "$rootDir/_git"

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
}
