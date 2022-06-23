package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxFriendByPublicKeyException(
    public val errorCode: ToxErrFriendByPublicKey,
) : RuntimeException(errorCode.name)
