package ltd.toktok.tox4j.errors

import java.lang.RuntimeException

public class ToxFriendCustomPacketException(
    public val errorCode: ToxErrFriendCustomPacket,
) : RuntimeException(errorCode.name)
