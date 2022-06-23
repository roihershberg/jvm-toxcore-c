package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxOptionsNewException(
    public val errorCode: ToxErrOptionsNew,
) : RuntimeException(errorCode.name)
