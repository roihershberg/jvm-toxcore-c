package ltd.toktok.tox4j.callbacks

import kotlin.String
import kotlin.Unit
import ltd.toktok.tox4j.Friend
import ltd.toktok.tox4j.Tox

public fun interface ToxFriendNameCb {
    public fun callback(
        tox: Tox,
        friend: Friend,
        name: String,
        userData: Unit,
    ): Unit
}
