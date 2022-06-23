package ltd.toktok.tox4j.gen

internal inline fun buildCodeString(builderAction: CodeWriter.() -> Unit): String {
    val stringBuilder = StringBuilder()
    CodeWriter(stringBuilder).builderAction()
    return stringBuilder.toString()
}

internal const val DEFAULT_INDENT = "    "

internal class CodeWriter(
    private val out: Appendable,
    private val indent: String = DEFAULT_INDENT,
) {
    private var indentLevel = 0

    fun indent(levels: Int = 1) = apply {
        indentLevel += levels
    }

    fun unindent(levels: Int = 1) = apply {
        require(indentLevel - levels >= 0) { "cannot unindent $levels from $indentLevel" }
        indentLevel -= levels
    }

    fun emitCode(codeBlock: CodeBlock) = apply {
        for (part in codeBlock.parts) {
            when (part) {
                "⇥" -> indent()
                "⇤" -> unindent()
                else -> emit(part)
            }
        }
    }

    fun emit(string: String) = apply {
        var first = true

        for (line in string.split('\n')) {
            if (!first) {
                out.append('\n')
                if (line.isEmpty()) continue
                emitIndentation()
            }
            first = false

            out.append(line)
        }
    }

    private fun emitIndentation() {
        for (i in 0 until indentLevel) {
            out.append(indent)
        }
    }
}