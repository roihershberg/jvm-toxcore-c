package ltd.toktok.tox4j.callbacks

import kotlin.Int
import kotlin.String
import kotlin.Unit
import ltd.toktok.tox4j.Tox
import ltd.toktok.tox4j.ToxLogLevel

public fun interface ToxLogCb {
    public fun callback(
        tox: Tox,
        level: ToxLogLevel,
        `file`: String,
        line: Int,
        func: String,
        message: String,
        userData: Unit,
    ): Unit
}
