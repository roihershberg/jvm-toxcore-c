package ltd.toktok.tox4j

import kotlin.Array
import kotlin.Byte
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.jvm.Throws
import ltd.toktok.tox4j.errors.ToxConferenceDeleteException
import ltd.toktok.tox4j.errors.ToxConferencePeerQueryException
import ltd.toktok.tox4j.errors.ToxConferenceSendMessageException
import ltd.toktok.tox4j.errors.ToxConferenceSetMaxOfflineException

public sealed interface Conference {
    public val title: String

    public val type: ToxConferenceType

    public val id: Array<Byte>

    public val uid: Array<Byte>

    @Throws(ToxConferenceDeleteException::class)
    public fun delete(): Unit

    @Throws(ToxConferencePeerQueryException::class)
    public fun peerCount(): Int

    @Throws(ToxConferencePeerQueryException::class)
    public fun peerIsOurs(peer: Peer): Unit

    @Throws(ToxConferencePeerQueryException::class)
    public fun offlinePeerCount(): Int

    @Throws(ToxConferenceSetMaxOfflineException::class)
    public fun setMaxOffline(maxOfflinePeers: Int): Unit

    @Throws(ToxConferenceSendMessageException::class)
    public fun sendMessage(type: ToxMessageType, message: String): Unit
}
