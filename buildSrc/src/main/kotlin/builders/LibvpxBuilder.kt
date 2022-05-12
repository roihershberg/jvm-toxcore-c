import org.gradle.api.tasks.TaskAction

abstract class LibvpxBuilder : DependencyBuilder() {
    @TaskAction
    override fun buildDep() {
        super.buildDep()

        configuration?.run {
            val env = configure(project, System.getenv(), buildDir, installDir)
            val libvpxSrcDir = "$srcDir/libvpx"
            val libvpxBuildDir = "$targetBuildDir/libvpx"

            project.file(libvpxBuildDir).mkdirs()
            Command(
                project,
                "$libvpxSrcDir/configure $libvpxConfigure"
            ).run(currentWorkingDir = project.file(libvpxBuildDir), env = env)

            Command(
                project,
                "$make -C $libvpxBuildDir install"
            ).run(env = env)
        } ?: throw NotConfiguredException(
            "Configuration",
            "withConfiguration"
        )
    }
}
