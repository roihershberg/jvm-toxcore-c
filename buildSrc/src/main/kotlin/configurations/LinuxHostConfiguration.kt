import org.gradle.api.Project

open class LinuxHostConfiguration : LinuxConfiguration() {
    override fun configure(
        project: Project,
        env: Map<String, String>,
        buildDir: String,
        installDir: String
    ): Map<String, String> {
        targetInstallDir = "$installDir/${OSChecker.platform}"
        targetBuildDir = "$buildDir/${OSChecker.platform}"
        libsodiumConfigure = "--prefix=$targetInstallDir --disable-shared"
        opusConfigure = "--prefix=$targetInstallDir --disable-shared"
        libvpxConfigure = "--prefix=$targetInstallDir --disable-examples --disable-unit-tests --enable-pic"
        toxcoreConfigure = "-DCMAKE_INSTALL_PREFIX:PATH=$targetInstallDir -DENABLE_STATIC=ON -DENABLE_SHARED=OFF"
        super.configure(project, env, installDir, buildDir)

        requireEither(project, "yasm", "nasm")
        return env + mapOf(
            "CC" to requireEither(project, "clang", "gcc"),
            "CXX" to requireEither(project, "clang++", "g++"),
            "PKG_CONFIG_PATH" to "$targetInstallDir/lib/pkgconfig",
        )
    }
}
