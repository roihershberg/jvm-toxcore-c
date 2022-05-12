import org.gradle.api.tasks.TaskAction

abstract class OpusBuilder : DependencyBuilder() {
    @TaskAction
    override fun buildDep() {
        super.buildDep()

        configuration?.run {
            val env = configure(project, System.getenv(), buildDir, installDir)
            val opusSrcDir = "$srcDir/opus"
            val opusBuildDir = "$targetBuildDir/opus"

            Command(project, "$autoreconf -fi").run(currentWorkingDir = project.file(opusSrcDir), env = env)
            project.file(opusBuildDir).mkdirs()

            Command(
                project,
                "$opusSrcDir/configure $opusConfigure"
            ).run(currentWorkingDir = project.file(opusBuildDir), env = env)

            Command(
                project,
                "$make -C $opusBuildDir install V=0"
            ).run(env = env)
        } ?: throw NotConfiguredException(
            "Configuration",
            "withConfiguration"
        )
    }
}
