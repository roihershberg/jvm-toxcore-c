package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxSetTypingException(
    public val errorCode: ToxErrSetTyping,
) : RuntimeException(errorCode.name)
