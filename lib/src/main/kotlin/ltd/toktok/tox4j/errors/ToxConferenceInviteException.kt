package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxConferenceInviteException(
    public val errorCode: ToxErrConferenceInvite,
) : RuntimeException(errorCode.name)
