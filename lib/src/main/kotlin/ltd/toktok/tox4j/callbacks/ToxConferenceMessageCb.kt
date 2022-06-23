package ltd.toktok.tox4j.callbacks

import kotlin.String
import kotlin.Unit
import ltd.toktok.tox4j.Conference
import ltd.toktok.tox4j.Peer
import ltd.toktok.tox4j.Tox
import ltd.toktok.tox4j.ToxMessageType

public fun interface ToxConferenceMessageCb {
    public fun callback(
        tox: Tox,
        conference: Conference,
        peer: Peer,
        type: ToxMessageType,
        message: String,
        userData: Unit,
    ): Unit
}
