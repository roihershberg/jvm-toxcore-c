package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxFriendGetLastOnlineException(
    public val errorCode: ToxErrFriendGetLastOnline,
) : RuntimeException(errorCode.name)
