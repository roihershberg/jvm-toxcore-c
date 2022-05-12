import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class ToxcoreBuilder : DependencyBuilder() {
    @TaskAction
    override fun buildDep() {
        super.buildDep()

        configuration?.run {
            val env = configure(project, System.getenv(), buildDir, installDir)
            val toxcoreSrcDir = "$srcDir/toxcore"
            val toxcoreBuildDir = "$targetBuildDir/toxcore"

            project.file(toxcoreBuildDir).mkdirs()
            Command(
                project,
                "$cmake $toxcoreSrcDir $toxcoreConfigure -DMUST_BUILD_TOXAV=ON -DBOOTSTRAP_DAEMON=OFF"
            ).run(currentWorkingDir = project.file(toxcoreBuildDir), env = env)

            Command(
                project,
                "$make -C $toxcoreBuildDir install"
            ).run(env = env)
        } ?: throw NotConfiguredException(
            "Configuration",
            "withConfiguration"
        )
    }
}
