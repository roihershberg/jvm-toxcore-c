package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxConferenceSendMessageException(
    public val errorCode: ToxErrConferenceSendMessage,
) : RuntimeException(errorCode.name)
