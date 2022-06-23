package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxFriendDeleteException(
    public val errorCode: ToxErrFriendDelete,
) : RuntimeException(errorCode.name)
