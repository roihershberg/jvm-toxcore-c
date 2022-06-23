package ltd.toktok.tox4j.callbacks

import kotlin.Array
import kotlin.Byte
import kotlin.Unit
import ltd.toktok.tox4j.Friend
import ltd.toktok.tox4j.Tox

public fun interface ToxFriendLossyPacketCb {
    public fun callback(
        tox: Tox,
        friend: Friend,
        `data`: Array<Byte>,
        userData: Unit,
    ): Unit
}
