package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxConferenceByIdException(
    public val errorCode: ToxErrConferenceById,
) : RuntimeException(errorCode.name)
