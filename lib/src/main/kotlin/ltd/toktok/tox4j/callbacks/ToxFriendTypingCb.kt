package ltd.toktok.tox4j.callbacks

import kotlin.Boolean
import kotlin.Unit
import ltd.toktok.tox4j.Friend
import ltd.toktok.tox4j.Tox

public fun interface ToxFriendTypingCb {
    public fun callback(
        tox: Tox,
        friend: Friend,
        typing: Boolean,
        userData: Unit,
    ): Unit
}
