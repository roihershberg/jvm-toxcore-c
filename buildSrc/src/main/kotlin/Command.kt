import org.gradle.api.Project
import java.io.ByteArrayOutputStream
import java.io.File

data class CommandResult(val exitCode: Int, val stdout: String)

enum class OutputMode {
    RETURN, PRINT
}

class Command(val project: Project, val command: List<String>) {
    constructor(project: Project, command: String) : this(project, command.split("\\s".toRegex()))

    fun run(
        currentWorkingDir: File = project.file("./"),
        outputMode: OutputMode = OutputMode.PRINT,
        crashOnError: Boolean = true
    ): CommandResult {
        val stdout = ByteArrayOutputStream()
        val execResult = project.exec {
            workingDir(currentWorkingDir)
            commandLine(command)
            isIgnoreExitValue = !crashOnError
            if (outputMode != OutputMode.PRINT) {
                standardOutput = stdout
            }
        }

        if (outputMode == OutputMode.RETURN) {
            return CommandResult(execResult.exitValue, stdout.toString().trim())
        } else {
            return CommandResult(execResult.exitValue, "")
        }
    }
}
