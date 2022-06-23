package ltd.toktok.tox4j.callbacks

import kotlin.String
import kotlin.Unit
import ltd.toktok.tox4j.Conference
import ltd.toktok.tox4j.Peer
import ltd.toktok.tox4j.Tox

public fun interface ToxConferenceTitleCb {
    public fun callback(
        tox: Tox,
        conference: Conference,
        peer: Peer,
        title: String,
        userData: Unit,
    ): Unit
}
