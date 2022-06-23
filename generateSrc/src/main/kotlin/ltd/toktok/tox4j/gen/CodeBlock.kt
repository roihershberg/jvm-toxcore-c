package ltd.toktok.tox4j.gen

internal val String.isIndentSymbol: Boolean get() = this == "⇥" || this == "⇤"
internal val Char.isIndentSymbol: Boolean get() = toString().isIndentSymbol

class CodeBlock {
    private val _parts: MutableList<String> = mutableListOf()
    internal val parts: List<String> get() = _parts

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (javaClass != other.javaClass) return false
        return toString() == other.toString()
    }

    override fun hashCode(): Int = toString().hashCode()

    override fun toString(): String = buildCodeString { emitCode(this@CodeBlock) }

    private fun appendCodeToParts(code: String) {
        if (_parts.isEmpty() || _parts.last().isIndentSymbol) {
            _parts.add(code)
        } else {
            _parts[_parts.lastIndex] += code
        }
    }

    fun add(code: String) = apply {
        val stringBuilder = StringBuilder()
        for (char in code) {
            if (char.isIndentSymbol) {
                if (stringBuilder.isNotEmpty()) {
                    appendCodeToParts(stringBuilder.toString())
                    stringBuilder.clear()
                }
                _parts.add(char.toString())
            } else {
                stringBuilder.append(char)
            }
        }

        if (stringBuilder.isNotEmpty()) {
            appendCodeToParts(stringBuilder.toString())
        }
    }

    fun indent() = apply {
        _parts += "⇥"
    }

    fun unindent() = apply {
        _parts += "⇤"
    }

    fun clear() = apply {
        _parts.clear()
    }

    operator fun String.unaryPlus() {
        add("\n" + this)
    }

    inline fun withIndent(codeBlockAction: CodeBlock.() -> Unit) {
        indent().also(codeBlockAction).unindent()
    }

    inline fun block(prefix: String, codeBlockAction: CodeBlock.() -> Unit) {
        add("\n$prefix${if (prefix.isEmpty()) "" else " "}{")
        withIndent {
            this.codeBlockAction()
        }
        add("\n}")
    }

    companion object {
        fun of(code: String): CodeBlock = CodeBlock().add(code)
    }
}

inline fun buildCodeBlock(codeBlockAction: CodeBlock.() -> Unit): CodeBlock =
    CodeBlock().also(codeBlockAction)
