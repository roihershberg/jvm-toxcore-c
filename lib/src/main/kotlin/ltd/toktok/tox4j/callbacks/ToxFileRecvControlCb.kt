package ltd.toktok.tox4j.callbacks

import kotlin.Unit
import ltd.toktok.tox4j.File
import ltd.toktok.tox4j.Friend
import ltd.toktok.tox4j.Tox
import ltd.toktok.tox4j.ToxFileControl

public fun interface ToxFileRecvControlCb {
    public fun callback(
        tox: Tox,
        friend: Friend,
        `file`: File,
        control: ToxFileControl,
        userData: Unit,
    ): Unit
}
