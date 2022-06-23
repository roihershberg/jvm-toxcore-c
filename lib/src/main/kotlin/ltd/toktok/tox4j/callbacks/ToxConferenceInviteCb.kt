package ltd.toktok.tox4j.callbacks

import kotlin.Array
import kotlin.Byte
import kotlin.Unit
import ltd.toktok.tox4j.Friend
import ltd.toktok.tox4j.Tox
import ltd.toktok.tox4j.ToxConferenceType

public fun interface ToxConferenceInviteCb {
    public fun callback(
        tox: Tox,
        friend: Friend,
        type: ToxConferenceType,
        cookie: Array<Byte>,
        userData: Unit,
    ): Unit
}
