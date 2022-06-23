package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxFileSeekException(
    public val errorCode: ToxErrFileSeek,
) : RuntimeException(errorCode.name)
