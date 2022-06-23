package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxConferenceSetMaxOfflineException(
    public val errorCode: ToxErrConferenceSetMaxOffline,
) : RuntimeException(errorCode.name)
