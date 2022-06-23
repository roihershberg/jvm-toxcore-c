package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxFileSendChunkException(
    public val errorCode: ToxErrFileSendChunk,
) : RuntimeException(errorCode.name)
