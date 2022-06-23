package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxFriendAddException(
    public val errorCode: ToxErrFriendAdd,
) : RuntimeException(errorCode.name)
