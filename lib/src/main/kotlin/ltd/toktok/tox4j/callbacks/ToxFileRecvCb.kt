package ltd.toktok.tox4j.callbacks

import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit
import ltd.toktok.tox4j.File
import ltd.toktok.tox4j.Friend
import ltd.toktok.tox4j.Tox

public fun interface ToxFileRecvCb {
    public fun callback(
        tox: Tox,
        friend: Friend,
        `file`: File,
        kind: Int,
        fileSize: Long,
        filename: String,
        userData: Unit,
    ): Unit
}
