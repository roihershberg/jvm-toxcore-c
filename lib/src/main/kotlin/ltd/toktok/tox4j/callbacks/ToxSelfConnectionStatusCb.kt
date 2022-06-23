package ltd.toktok.tox4j.callbacks

import kotlin.Unit
import ltd.toktok.tox4j.Tox
import ltd.toktok.tox4j.ToxConnection

public fun interface ToxSelfConnectionStatusCb {
    public fun callback(
        tox: Tox,
        connectionStatus: ToxConnection,
        userData: Unit,
    ): Unit
}
