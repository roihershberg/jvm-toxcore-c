import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input

abstract class DependencyBuilder : DefaultTask() {
    @get:Input
    protected var directory: String = ""
    @get:Input
    protected var srcDir: String = ""
    @get:Input
    protected var buildDir: String = ""
    @get:Input
    protected var installDir: String = ""
    @get:Input
    protected var configuration: Configuration? = null

    fun build(directory: String) {
        this.directory = directory
    }

    fun from(srcDir: String) {
        this.srcDir = srcDir
    }

    fun to(buildDir: String) {
        this.buildDir = buildDir
    }

    fun installTo(installDir: String) {
        this.installDir = installDir
    }

    fun withConfiguration(configuration: Configuration) {
        this.configuration = configuration
    }

    open fun buildDep() {
        if (directory.isEmpty()) throw NotConfiguredException("Dependency directory", "build")
        if (srcDir.isEmpty()) throw NotConfiguredException("Source directory", "from")
        if (buildDir.isEmpty()) throw NotConfiguredException("Build directory", "to")
        if (installDir.isEmpty()) throw NotConfiguredException("Install directory", "installTo")
    }
}
