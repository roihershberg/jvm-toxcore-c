import org.gradle.api.Project

abstract class LinuxConfiguration : CommonConfiguration() {
    override fun configure(
        project: Project,
        env: Map<String, String>,
        buildDir: String,
        installDir: String
    ): Map<String, String> {
        super.configure(project, env, buildDir, installDir)
        return env + mapOf(
            "DLLEXT" to ".so",
            "CFLAGS" to env["CFLAGS"] + " -fPIC",
            "CXXFLAGS" to env["CXXFLAGS"] + "-fPIC",
        )
    }
}
