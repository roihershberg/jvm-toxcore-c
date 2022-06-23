package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxConferenceGetTypeException(
    public val errorCode: ToxErrConferenceGetType,
) : RuntimeException(errorCode.name)
