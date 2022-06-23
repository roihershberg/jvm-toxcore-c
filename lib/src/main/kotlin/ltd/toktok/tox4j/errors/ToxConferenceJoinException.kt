package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxConferenceJoinException(
    public val errorCode: ToxErrConferenceJoin,
) : RuntimeException(errorCode.name)
