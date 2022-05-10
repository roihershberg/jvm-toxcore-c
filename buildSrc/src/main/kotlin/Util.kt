import org.gradle.api.Project
import java.io.File

/*
 Requires the presence of at least one command from the given list.
 At failure -> exception thrown.
 At success -> the absolute path of the found command is returned.
 */
@Throws(OSNotSupportedException::class, CommandsNotFoundException::class)
internal fun requireEither(project: Project, vararg commands: String): String {
    val cmdGetPathOfCommand: List<String> = when (OSChecker.os) {
        OS.Windows -> listOf("where")
        OS.Linux, OS.`Mac OS X` -> listOf("which")
        OS.Unknown -> throw OSNotSupportedException()
    }

    val commandAbsolutePath = commands.mapNotNull {
        val (exitCode, stdout) = Command(project, cmdGetPathOfCommand + it).run(
            outputMode = OutputMode.RETURN,
            crashOnError = false
        )
        if (exitCode == 0 && !stdout.isEmpty()) stdout else null
    }.firstOrNull() ?: throw CommandsNotFoundException(commands.toList())

    return commandAbsolutePath.trim('\n', ' ')
}
