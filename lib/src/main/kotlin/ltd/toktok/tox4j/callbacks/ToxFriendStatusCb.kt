package ltd.toktok.tox4j.callbacks

import kotlin.Unit
import ltd.toktok.tox4j.Friend
import ltd.toktok.tox4j.Tox
import ltd.toktok.tox4j.ToxUserStatus

public fun interface ToxFriendStatusCb {
    public fun callback(
        tox: Tox,
        friend: Friend,
        status: ToxUserStatus,
        userData: Unit,
    ): Unit
}
