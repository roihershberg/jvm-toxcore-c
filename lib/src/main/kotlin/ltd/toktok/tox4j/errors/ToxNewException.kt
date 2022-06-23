package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxNewException(
    public val errorCode: ToxErrNew,
) : RuntimeException(errorCode.name)
