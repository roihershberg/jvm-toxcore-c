import java.lang.RuntimeException

class CommandsNotFoundException(commands: List<String>) : RuntimeException() {
    override val message: String? =
        if (commands.size == 1)
            "Cannot execute command '${commands[0]}'. Please ensure it's installed correctly."
        else
            "None of these commands were found: $commands. Please ensure at least one of them is installed correctly."
}
