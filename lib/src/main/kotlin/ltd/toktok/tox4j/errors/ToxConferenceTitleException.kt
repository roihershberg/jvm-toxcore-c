package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxConferenceTitleException(
    public val errorCode: ToxErrConferenceTitle,
) : RuntimeException(errorCode.name)
