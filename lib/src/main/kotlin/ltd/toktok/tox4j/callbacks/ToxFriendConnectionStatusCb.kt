package ltd.toktok.tox4j.callbacks

import kotlin.Unit
import ltd.toktok.tox4j.Friend
import ltd.toktok.tox4j.Tox
import ltd.toktok.tox4j.ToxConnection

public fun interface ToxFriendConnectionStatusCb {
    public fun callback(
        tox: Tox,
        friend: Friend,
        connectionStatus: ToxConnection,
        userData: Unit,
    ): Unit
}
