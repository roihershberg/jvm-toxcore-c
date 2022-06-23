package ltd.toktok.tox4j.callbacks

import kotlin.String
import kotlin.Unit
import ltd.toktok.tox4j.Friend
import ltd.toktok.tox4j.Tox

public fun interface ToxFriendStatusMessageCb {
    public fun callback(
        tox: Tox,
        friend: Friend,
        message: String,
        userData: Unit,
    ): Unit
}
