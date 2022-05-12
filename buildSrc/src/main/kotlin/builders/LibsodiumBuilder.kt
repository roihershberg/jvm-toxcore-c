import org.gradle.api.tasks.TaskAction

abstract class LibsodiumBuilder : DependencyBuilder() {
    @TaskAction
    override fun buildDep() {
        super.buildDep()

        configuration?.run {
            val env = configure(project, System.getenv(), buildDir, installDir)
            val libsodiumSrcDir = "$srcDir/libsodium"
            val libsodiumBuildDir = "$targetBuildDir/libsodium"

            Command(project, "$autoreconf -fi").run(currentWorkingDir = project.file(libsodiumSrcDir), env = env)
            project.file(libsodiumBuildDir).mkdirs()

            Command(
                project,
                "$libsodiumSrcDir/configure $libsodiumConfigure"
            ).run(currentWorkingDir = project.file(libsodiumBuildDir), env = env)

            Command(
                project,
                "$make -C $libsodiumBuildDir install V=0"
            ).run(env = env)
        } ?: throw NotConfiguredException(
            "Configuration",
            "withConfiguration"
        )
    }
}
