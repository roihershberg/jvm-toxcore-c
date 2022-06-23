package ltd.toktok.tox4j

import kotlin.Array
import kotlin.Boolean
import kotlin.Byte
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlin.jvm.Throws
import ltd.toktok.tox4j.errors.ToxConferenceInviteException
import ltd.toktok.tox4j.errors.ToxConferenceJoinException
import ltd.toktok.tox4j.errors.ToxFileSendException
import ltd.toktok.tox4j.errors.ToxFriendCustomPacketException
import ltd.toktok.tox4j.errors.ToxFriendDeleteException
import ltd.toktok.tox4j.errors.ToxFriendSendMessageException
import ltd.toktok.tox4j.errors.ToxSetTypingException

public sealed interface Friend {
    public val publicKey: Array<Byte>

    public val lastOnline: Long

    public val name: String

    public val statusMessage: String

    public val status: ToxUserStatus

    public val connectionStatus: ToxConnection

    public val typing: Boolean

    @Throws(ToxFriendDeleteException::class)
    public fun delete(): Unit

    public fun exists(): Boolean

    @Throws(ToxSetTypingException::class)
    public fun selfSetTyping(typing: Boolean): Unit

    @Throws(ToxFriendSendMessageException::class)
    public fun sendMessage(type: ToxMessageType, message: String): Int

    @Throws(ToxFileSendException::class)
    public fun sendFile(
        kind: Int,
        fileSize: Long,
        fileId: Array<Byte>,
        filename: String,
    ): File

    @Throws(ToxConferenceInviteException::class)
    public fun conferenceInvite(conference: Conference): Unit

    @Throws(ToxConferenceJoinException::class)
    public fun conferenceJoin(cookie: Array<Byte>): Conference

    @Throws(ToxFriendCustomPacketException::class)
    public fun sendLossyPacket(`data`: Array<Byte>): Unit

    @Throws(ToxFriendCustomPacketException::class)
    public fun sendLosslessPacket(`data`: Array<Byte>): Unit
}
