package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxFriendGetPublicKeyException(
    public val errorCode: ToxErrFriendGetPublicKey,
) : RuntimeException(errorCode.name)
