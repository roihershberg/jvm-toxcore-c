package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxFriendQueryException(
    public val errorCode: ToxErrFriendQuery,
) : RuntimeException(errorCode.name)
