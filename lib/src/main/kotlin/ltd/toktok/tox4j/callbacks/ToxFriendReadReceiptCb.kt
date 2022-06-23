package ltd.toktok.tox4j.callbacks

import kotlin.Int
import kotlin.Unit
import ltd.toktok.tox4j.Friend
import ltd.toktok.tox4j.Tox

public fun interface ToxFriendReadReceiptCb {
    public fun callback(
        tox: Tox,
        friend: Friend,
        messageId: Int,
        userData: Unit,
    ): Unit
}
