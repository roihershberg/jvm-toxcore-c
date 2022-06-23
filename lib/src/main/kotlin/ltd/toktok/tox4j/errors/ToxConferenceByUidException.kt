package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxConferenceByUidException(
    public val errorCode: ToxErrConferenceByUid,
) : RuntimeException(errorCode.name)
