package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxGetPortException(
    public val errorCode: ToxErrGetPort,
) : RuntimeException(errorCode.name)
