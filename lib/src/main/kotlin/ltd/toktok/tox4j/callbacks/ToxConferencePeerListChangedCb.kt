package ltd.toktok.tox4j.callbacks

import kotlin.Unit
import ltd.toktok.tox4j.Conference
import ltd.toktok.tox4j.Tox

public fun interface ToxConferencePeerListChangedCb {
    public fun callback(
        tox: Tox,
        conference: Conference,
        userData: Unit,
    ): Unit
}
