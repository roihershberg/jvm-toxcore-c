package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxConferencePeerQueryException(
    public val errorCode: ToxErrConferencePeerQuery,
) : RuntimeException(errorCode.name)
