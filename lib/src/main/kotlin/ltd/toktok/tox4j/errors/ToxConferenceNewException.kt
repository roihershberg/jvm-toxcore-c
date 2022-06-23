package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxConferenceNewException(
    public val errorCode: ToxErrConferenceNew,
) : RuntimeException(errorCode.name)
