import org.gradle.api.Project

abstract class Configuration {
    lateinit var targetInstallDir: String
        protected set

    lateinit var targetBuildDir: String
        protected set

    // Dependency configurations

    lateinit var libsodiumConfigure: String
        protected set

    lateinit var opusConfigure: String
        protected set

    lateinit var libvpxConfigure: String
        protected set

    lateinit var toxcoreConfigure: String
        protected set

    // Utilities

    lateinit var make: String
        protected set

    lateinit var cmake: String
        protected set

    lateinit var autoreconf: String
        protected set

    abstract fun configure(
        project: Project,
        env: Map<String, String>,
        buildDir: String,
        installDir: String
    ): Map<String, String>
}
