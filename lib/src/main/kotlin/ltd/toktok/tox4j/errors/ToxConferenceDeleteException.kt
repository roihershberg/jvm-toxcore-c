package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxConferenceDeleteException(
    public val errorCode: ToxErrConferenceDelete,
) : RuntimeException(errorCode.name)
