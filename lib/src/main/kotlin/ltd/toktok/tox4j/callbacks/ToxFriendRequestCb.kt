package ltd.toktok.tox4j.callbacks

import kotlin.Array
import kotlin.Byte
import kotlin.String
import kotlin.Unit
import ltd.toktok.tox4j.Tox

public fun interface ToxFriendRequestCb {
    public fun callback(
        tox: Tox,
        publicKey: Array<Byte>,
        message: String,
        userData: Unit,
    ): Unit
}
