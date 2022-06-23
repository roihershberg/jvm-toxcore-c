package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxFileSendException(
    public val errorCode: ToxErrFileSend,
) : RuntimeException(errorCode.name)
