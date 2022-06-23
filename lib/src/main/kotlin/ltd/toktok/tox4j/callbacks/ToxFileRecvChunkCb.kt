package ltd.toktok.tox4j.callbacks

import kotlin.Array
import kotlin.Byte
import kotlin.Long
import kotlin.Unit
import ltd.toktok.tox4j.File
import ltd.toktok.tox4j.Friend
import ltd.toktok.tox4j.Tox

public fun interface ToxFileRecvChunkCb {
    public fun callback(
        tox: Tox,
        friend: Friend,
        `file`: File,
        position: Long,
        `data`: Array<Byte>,
        userData: Unit,
    ): Unit
}
