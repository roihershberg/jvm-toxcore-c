import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class GitFetcher : DefaultTask() {
    private var url: String = ""

    private var directory: String = ""

    private var branch: String = ""

    private var depth: Int = 0

    private var recursive: Boolean = false

    private val patches: MutableList<String> = mutableListOf()

    private val git = requireEither(project, "git")

    fun from(url: String) {
        this.url = url
    }

    fun to(directory: String) {
        this.directory = directory
    }

    fun branch(branch: String) {
        this.branch = branch
    }

    fun depth(depth: Int) {
        this.depth = depth
    }

    fun recursive() {
        this.recursive = true
    }

    fun withPatch(pathOfPatch: String) {
        patches.add(pathOfPatch)
    }

    init {
        description = "Fetches the git repository of the required dependency if the directory doesn't already exist."
    }

    @TaskAction
    fun fetchGitRepo() {
        if (url.isEmpty()) throw NotConfiguredException("Git URL", "from")
        if (directory.isEmpty()) throw NotConfiguredException("Git clone output directory", "to")

        if (project.file(directory).exists()) return

        val cmd = mutableListOf(git, "clone").apply {
            if (!branch.isEmpty()) add("--branch=$branch")
            if (depth != 0) add("--depth=$depth")
            if (recursive) add("--recursive")
            add(url)
            add(directory)
        }

        Command(project, cmd).run()
        patches.forEach {
            Command(project, "$git am $it").run(currentWorkingDir = project.file(directory))
        }
    }
}
