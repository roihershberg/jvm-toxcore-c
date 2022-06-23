package ltd.toktok.tox4j.callbacks

import kotlin.String
import kotlin.Unit
import ltd.toktok.tox4j.Friend
import ltd.toktok.tox4j.Tox
import ltd.toktok.tox4j.ToxMessageType

public fun interface ToxFriendMessageCb {
    public fun callback(
        tox: Tox,
        friend: Friend,
        type: ToxMessageType,
        message: String,
        userData: Unit,
    ): Unit
}
