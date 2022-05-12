import org.gradle.api.Project

abstract class CommonConfiguration : Configuration() {
    override fun configure(
        project: Project,
        env: Map<String, String>,
        buildDir: String,
        installDir: String
    ): Map<String, String> {
        make = requireEither(project, "make")
        cmake = requireEither(project, "cmake")
        autoreconf = requireEither(project, "autoreconf")

        return env + mapOf(
            "CFLAGS" to "-O3 -pipe",
            "CXXFLAGS" to "-O3 -pipe",
            "LDFLAGS" to "",
        )
    }
}
