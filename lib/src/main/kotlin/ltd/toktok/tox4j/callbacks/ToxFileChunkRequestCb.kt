package ltd.toktok.tox4j.callbacks

import kotlin.Long
import kotlin.Unit
import ltd.toktok.tox4j.File
import ltd.toktok.tox4j.Friend
import ltd.toktok.tox4j.Tox

public fun interface ToxFileChunkRequestCb {
    public fun callback(
        tox: Tox,
        friend: Friend,
        `file`: File,
        position: Long,
        length: Long,
        userData: Unit,
    ): Unit
}
