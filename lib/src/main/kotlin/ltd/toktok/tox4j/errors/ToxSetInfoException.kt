package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxSetInfoException(
    public val errorCode: ToxErrSetInfo,
) : RuntimeException(errorCode.name)
