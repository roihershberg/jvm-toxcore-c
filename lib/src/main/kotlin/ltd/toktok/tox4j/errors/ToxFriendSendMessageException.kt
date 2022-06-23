package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxFriendSendMessageException(
    public val errorCode: ToxErrFriendSendMessage,
) : RuntimeException(errorCode.name)
