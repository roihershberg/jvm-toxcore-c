package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxFileGetException(
    public val errorCode: ToxErrFileGet,
) : RuntimeException(errorCode.name)
