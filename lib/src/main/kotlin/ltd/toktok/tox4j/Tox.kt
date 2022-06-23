package ltd.toktok.tox4j

import kotlin.Array
import kotlin.Boolean
import kotlin.Byte
import kotlin.Int
import kotlin.Long
import kotlin.Short
import kotlin.String
import kotlin.Unit
import kotlin.jvm.JvmStatic
import kotlin.jvm.Throws
import ltd.toktok.tox4j.callbacks.ToxConferenceConnectedCb
import ltd.toktok.tox4j.callbacks.ToxConferenceInviteCb
import ltd.toktok.tox4j.callbacks.ToxConferenceMessageCb
import ltd.toktok.tox4j.callbacks.ToxConferencePeerListChangedCb
import ltd.toktok.tox4j.callbacks.ToxConferencePeerNameCb
import ltd.toktok.tox4j.callbacks.ToxConferenceTitleCb
import ltd.toktok.tox4j.callbacks.ToxFileChunkRequestCb
import ltd.toktok.tox4j.callbacks.ToxFileRecvCb
import ltd.toktok.tox4j.callbacks.ToxFileRecvChunkCb
import ltd.toktok.tox4j.callbacks.ToxFileRecvControlCb
import ltd.toktok.tox4j.callbacks.ToxFriendConnectionStatusCb
import ltd.toktok.tox4j.callbacks.ToxFriendLosslessPacketCb
import ltd.toktok.tox4j.callbacks.ToxFriendLossyPacketCb
import ltd.toktok.tox4j.callbacks.ToxFriendMessageCb
import ltd.toktok.tox4j.callbacks.ToxFriendNameCb
import ltd.toktok.tox4j.callbacks.ToxFriendReadReceiptCb
import ltd.toktok.tox4j.callbacks.ToxFriendRequestCb
import ltd.toktok.tox4j.callbacks.ToxFriendStatusCb
import ltd.toktok.tox4j.callbacks.ToxFriendStatusMessageCb
import ltd.toktok.tox4j.callbacks.ToxFriendTypingCb
import ltd.toktok.tox4j.callbacks.ToxSelfConnectionStatusCb
import ltd.toktok.tox4j.errors.ToxBootstrapException
import ltd.toktok.tox4j.errors.ToxConferenceByIdException
import ltd.toktok.tox4j.errors.ToxConferenceByUidException
import ltd.toktok.tox4j.errors.ToxConferenceDeleteException
import ltd.toktok.tox4j.errors.ToxConferenceGetTypeException
import ltd.toktok.tox4j.errors.ToxConferenceInviteException
import ltd.toktok.tox4j.errors.ToxConferenceJoinException
import ltd.toktok.tox4j.errors.ToxConferenceNewException
import ltd.toktok.tox4j.errors.ToxConferencePeerQueryException
import ltd.toktok.tox4j.errors.ToxConferenceSendMessageException
import ltd.toktok.tox4j.errors.ToxConferenceSetMaxOfflineException
import ltd.toktok.tox4j.errors.ToxConferenceTitleException
import ltd.toktok.tox4j.errors.ToxFileControlException
import ltd.toktok.tox4j.errors.ToxFileGetException
import ltd.toktok.tox4j.errors.ToxFileSeekException
import ltd.toktok.tox4j.errors.ToxFileSendChunkException
import ltd.toktok.tox4j.errors.ToxFileSendException
import ltd.toktok.tox4j.errors.ToxFriendAddException
import ltd.toktok.tox4j.errors.ToxFriendByPublicKeyException
import ltd.toktok.tox4j.errors.ToxFriendCustomPacketException
import ltd.toktok.tox4j.errors.ToxFriendDeleteException
import ltd.toktok.tox4j.errors.ToxFriendGetLastOnlineException
import ltd.toktok.tox4j.errors.ToxFriendGetPublicKeyException
import ltd.toktok.tox4j.errors.ToxFriendQueryException
import ltd.toktok.tox4j.errors.ToxFriendSendMessageException
import ltd.toktok.tox4j.errors.ToxGetPortException
import ltd.toktok.tox4j.errors.ToxNewException
import ltd.toktok.tox4j.errors.ToxSetInfoException
import ltd.toktok.tox4j.errors.ToxSetTypingException

public class Tox(
    options: ToxOptions,
) {
    internal val handle: Long = allocateNative(options.handle)

    public val savedata: Array<Byte>
        get() = getSavedata(
            this@Tox.handle,
        )

    public val selfConnectionStatus: ToxConnection
        get() = getSelfConnectionStatus(
            this@Tox.handle,
        )

    public val selfAddress: Array<Byte>
        get() = getSelfAddress(
            this@Tox.handle,
        )

    public var selfNospam: Int
        get() = getSelfNospam(
            this@Tox.handle,
        )
        set(nospam) = setSelfNospam(
            this@Tox.handle,
            nospam,
        )

    public val selfPublicKey: Array<Byte>
        get() = getSelfPublicKey(
            this@Tox.handle,
        )

    public val selfSecretKey: Array<Byte>
        get() = getSelfSecretKey(
            this@Tox.handle,
        )

    @set:Throws(ToxSetInfoException::class)
    public var selfName: String
        get() = getSelfName(
            this@Tox.handle,
        )
        set(name) = setSelfName(
            this@Tox.handle,
            name,
        )

    @set:Throws(ToxSetInfoException::class)
    public var selfStatusMessage: String
        get() = getSelfStatusMessage(
            this@Tox.handle,
        )
        set(statusMessage) = setSelfStatusMessage(
            this@Tox.handle,
            statusMessage,
        )

    public var selfStatus: ToxUserStatus
        get() = getSelfStatus(
            this@Tox.handle,
        )
        set(status) = setSelfStatus(
            this@Tox.handle,
            status,
        )

    public val selfFriendList: Array<Friend>
        get() = getSelfFriendList(
            this@Tox.handle,
        )

    public val conferenceChatlist: Array<Conference>
        get() = getConferenceChatlist(
            this@Tox.handle,
        )

    public val selfDhtId: Array<Byte>
        get() = getSelfDhtId(
            this@Tox.handle,
        )

    @get:Throws(ToxGetPortException::class)
    public val selfUdpPort: Short
        get() = getSelfUdpPort(
            this@Tox.handle,
        )

    @get:Throws(ToxGetPortException::class)
    public val selfTcpPort: Short
        get() = getSelfTcpPort(
            this@Tox.handle,
        )

    public val iterationInterval: Int
        get() = getIterationInterval(
            this@Tox.handle,
        )

    private external fun getSavedata(toxHandle: Long): Array<Byte>

    private external fun getSelfConnectionStatus(toxHandle: Long): ToxConnection

    private external fun getSelfAddress(toxHandle: Long): Array<Byte>

    private external fun getSelfNospam(toxHandle: Long): Int

    private external fun setSelfNospam(toxHandle: Long, nospam: Int): Unit

    private external fun getSelfPublicKey(toxHandle: Long): Array<Byte>

    private external fun getSelfSecretKey(toxHandle: Long): Array<Byte>

    private external fun getSelfName(toxHandle: Long): String

    @Throws(ToxSetInfoException::class)
    private external fun setSelfName(toxHandle: Long, name: String): Unit

    private external fun getSelfStatusMessage(toxHandle: Long): String

    @Throws(ToxSetInfoException::class)
    private external fun setSelfStatusMessage(toxHandle: Long, statusMessage: String): Unit

    private external fun getSelfStatus(toxHandle: Long): ToxUserStatus

    private external fun setSelfStatus(toxHandle: Long, status: ToxUserStatus): Unit

    private external fun getSelfFriendList(toxHandle: Long): Array<Friend>

    private external fun getConferenceChatlist(toxHandle: Long): Array<Conference>

    private external fun getSelfDhtId(toxHandle: Long): Array<Byte>

    @Throws(ToxGetPortException::class)
    private external fun getSelfUdpPort(toxHandle: Long): Short

    @Throws(ToxGetPortException::class)
    private external fun getSelfTcpPort(toxHandle: Long): Short

    private external fun getIterationInterval(toxHandle: Long): Int

    @Throws(ToxBootstrapException::class)
    public fun bootstrap(
        host: String,
        port: Short,
        publicKey: Array<Byte>,
    ): Unit = 
        bootstrap(
            this@Tox.handle,
            host,
            port,
            publicKey,
        )

    @Throws(ToxBootstrapException::class)
    private external fun bootstrap(
        toxHandle: Long,
        host: String,
        port: Short,
        publicKey: Array<Byte>,
    ): Unit

    @Throws(ToxBootstrapException::class)
    public fun addTcpRelay(
        host: String,
        port: Short,
        publicKey: Array<Byte>,
    ): Unit = 
        addTcpRelay(
            this@Tox.handle,
            host,
            port,
            publicKey,
        )

    @Throws(ToxBootstrapException::class)
    private external fun addTcpRelay(
        toxHandle: Long,
        host: String,
        port: Short,
        publicKey: Array<Byte>,
    ): Unit

    public fun callbackSelfConnectionStatus(callback: ToxSelfConnectionStatusCb): Unit = 
        callbackSelfConnectionStatus(
            this@Tox.handle,
            callback,
        )

    private external fun callbackSelfConnectionStatus(toxHandle: Long,
            callback: ToxSelfConnectionStatusCb): Unit

    public fun iterate(userData: Unit): Unit = 
        iterate(
            this@Tox.handle,
            userData,
        )

    private external fun iterate(toxHandle: Long, userData: Unit): Unit

    @Throws(ToxFriendAddException::class)
    public fun addFriend(address: Array<Byte>, message: String): Friend = 
        addFriend(
            this@Tox.handle,
            address,
            message,
        )

    @Throws(ToxFriendAddException::class)
    private external fun addFriend(
        toxHandle: Long,
        address: Array<Byte>,
        message: String,
    ): Friend

    @Throws(ToxFriendAddException::class)
    public fun addFriendNorequest(publicKey: Array<Byte>): Friend = 
        addFriendNorequest(
            this@Tox.handle,
            publicKey,
        )

    @Throws(ToxFriendAddException::class)
    private external fun addFriendNorequest(toxHandle: Long, publicKey: Array<Byte>): Friend

    @Throws(ToxFriendByPublicKeyException::class)
    public fun friendByPublicKey(publicKey: Array<Byte>): Friend = 
        friendByPublicKey(
            this@Tox.handle,
            publicKey,
        )

    @Throws(ToxFriendByPublicKeyException::class)
    private external fun friendByPublicKey(toxHandle: Long, publicKey: Array<Byte>): Friend

    public fun callbackFriendName(callback: ToxFriendNameCb): Unit = 
        callbackFriendName(
            this@Tox.handle,
            callback,
        )

    private external fun callbackFriendName(toxHandle: Long, callback: ToxFriendNameCb): Unit

    public fun callbackFriendStatusMessage(callback: ToxFriendStatusMessageCb): Unit = 
        callbackFriendStatusMessage(
            this@Tox.handle,
            callback,
        )

    private external fun callbackFriendStatusMessage(toxHandle: Long,
            callback: ToxFriendStatusMessageCb): Unit

    public fun callbackFriendStatus(callback: ToxFriendStatusCb): Unit = 
        callbackFriendStatus(
            this@Tox.handle,
            callback,
        )

    private external fun callbackFriendStatus(toxHandle: Long, callback: ToxFriendStatusCb): Unit

    public fun callbackFriendConnectionStatus(callback: ToxFriendConnectionStatusCb): Unit = 
        callbackFriendConnectionStatus(
            this@Tox.handle,
            callback,
        )

    private external fun callbackFriendConnectionStatus(toxHandle: Long,
            callback: ToxFriendConnectionStatusCb): Unit

    public fun callbackFriendTyping(callback: ToxFriendTypingCb): Unit = 
        callbackFriendTyping(
            this@Tox.handle,
            callback,
        )

    private external fun callbackFriendTyping(toxHandle: Long, callback: ToxFriendTypingCb): Unit

    public fun callbackFriendReadReceipt(callback: ToxFriendReadReceiptCb): Unit = 
        callbackFriendReadReceipt(
            this@Tox.handle,
            callback,
        )

    private external fun callbackFriendReadReceipt(toxHandle: Long,
            callback: ToxFriendReadReceiptCb): Unit

    public fun callbackFriendRequest(callback: ToxFriendRequestCb): Unit = 
        callbackFriendRequest(
            this@Tox.handle,
            callback,
        )

    private external fun callbackFriendRequest(toxHandle: Long, callback: ToxFriendRequestCb): Unit

    public fun callbackFriendMessage(callback: ToxFriendMessageCb): Unit = 
        callbackFriendMessage(
            this@Tox.handle,
            callback,
        )

    private external fun callbackFriendMessage(toxHandle: Long, callback: ToxFriendMessageCb): Unit

    public fun callbackFileRecvControl(callback: ToxFileRecvControlCb): Unit = 
        callbackFileRecvControl(
            this@Tox.handle,
            callback,
        )

    private external fun callbackFileRecvControl(toxHandle: Long, callback: ToxFileRecvControlCb):
            Unit

    public fun callbackFileChunkRequest(callback: ToxFileChunkRequestCb): Unit = 
        callbackFileChunkRequest(
            this@Tox.handle,
            callback,
        )

    private external fun callbackFileChunkRequest(toxHandle: Long, callback: ToxFileChunkRequestCb):
            Unit

    public fun callbackFileRecv(callback: ToxFileRecvCb): Unit = 
        callbackFileRecv(
            this@Tox.handle,
            callback,
        )

    private external fun callbackFileRecv(toxHandle: Long, callback: ToxFileRecvCb): Unit

    public fun callbackFileRecvChunk(callback: ToxFileRecvChunkCb): Unit = 
        callbackFileRecvChunk(
            this@Tox.handle,
            callback,
        )

    private external fun callbackFileRecvChunk(toxHandle: Long, callback: ToxFileRecvChunkCb): Unit

    public fun callbackConferenceInvite(callback: ToxConferenceInviteCb): Unit = 
        callbackConferenceInvite(
            this@Tox.handle,
            callback,
        )

    private external fun callbackConferenceInvite(toxHandle: Long, callback: ToxConferenceInviteCb):
            Unit

    public fun callbackConferenceConnected(callback: ToxConferenceConnectedCb): Unit = 
        callbackConferenceConnected(
            this@Tox.handle,
            callback,
        )

    private external fun callbackConferenceConnected(toxHandle: Long,
            callback: ToxConferenceConnectedCb): Unit

    public fun callbackConferenceMessage(callback: ToxConferenceMessageCb): Unit = 
        callbackConferenceMessage(
            this@Tox.handle,
            callback,
        )

    private external fun callbackConferenceMessage(toxHandle: Long,
            callback: ToxConferenceMessageCb): Unit

    public fun callbackConferenceTitle(callback: ToxConferenceTitleCb): Unit = 
        callbackConferenceTitle(
            this@Tox.handle,
            callback,
        )

    private external fun callbackConferenceTitle(toxHandle: Long, callback: ToxConferenceTitleCb):
            Unit

    public fun callbackConferencePeerName(callback: ToxConferencePeerNameCb): Unit = 
        callbackConferencePeerName(
            this@Tox.handle,
            callback,
        )

    private external fun callbackConferencePeerName(toxHandle: Long,
            callback: ToxConferencePeerNameCb): Unit

    public fun callbackConferencePeerListChanged(callback: ToxConferencePeerListChangedCb): Unit = 
        callbackConferencePeerListChanged(
            this@Tox.handle,
            callback,
        )

    private external fun callbackConferencePeerListChanged(toxHandle: Long,
            callback: ToxConferencePeerListChangedCb): Unit

    @Throws(ToxConferenceNewException::class)
    public fun newConference(): Conference = 
        newConference(
            this@Tox.handle,
        )

    @Throws(ToxConferenceNewException::class)
    private external fun newConference(toxHandle: Long): Conference

    @Throws(ToxConferenceByIdException::class)
    public fun conferenceById(id: Array<Byte>): Conference = 
        conferenceById(
            this@Tox.handle,
            id,
        )

    @Throws(ToxConferenceByIdException::class)
    private external fun conferenceById(toxHandle: Long, id: Array<Byte>): Conference

    @Throws(ToxConferenceByUidException::class)
    public fun conferenceByUid(uid: Array<Byte>): Conference = 
        conferenceByUid(
            this@Tox.handle,
            uid,
        )

    @Throws(ToxConferenceByUidException::class)
    private external fun conferenceByUid(toxHandle: Long, uid: Array<Byte>): Conference

    public fun callbackFriendLossyPacket(callback: ToxFriendLossyPacketCb): Unit = 
        callbackFriendLossyPacket(
            this@Tox.handle,
            callback,
        )

    private external fun callbackFriendLossyPacket(toxHandle: Long,
            callback: ToxFriendLossyPacketCb): Unit

    public fun callbackFriendLosslessPacket(callback: ToxFriendLosslessPacketCb): Unit = 
        callbackFriendLosslessPacket(
            this@Tox.handle,
            callback,
        )

    private external fun callbackFriendLosslessPacket(toxHandle: Long,
            callback: ToxFriendLosslessPacketCb): Unit

    internal inner class FriendImpl private constructor(
        internal val handle: Int,
    ) : Friend {
        @get:Throws(ToxFriendGetPublicKeyException::class)
        public override val publicKey: Array<Byte>
            get() = getPublicKey(
                this@Tox.handle,
                this@FriendImpl.handle,
            )

        @get:Throws(ToxFriendGetLastOnlineException::class)
        public override val lastOnline: Long
            get() = getLastOnline(
                this@Tox.handle,
                this@FriendImpl.handle,
            )

        @get:Throws(ToxFriendQueryException::class)
        public override val name: String
            get() = getName(
                this@Tox.handle,
                this@FriendImpl.handle,
            )

        @get:Throws(ToxFriendQueryException::class)
        public override val statusMessage: String
            get() = getStatusMessage(
                this@Tox.handle,
                this@FriendImpl.handle,
            )

        @get:Throws(ToxFriendQueryException::class)
        public override val status: ToxUserStatus
            get() = getStatus(
                this@Tox.handle,
                this@FriendImpl.handle,
            )

        @get:Throws(ToxFriendQueryException::class)
        public override val connectionStatus: ToxConnection
            get() = getConnectionStatus(
                this@Tox.handle,
                this@FriendImpl.handle,
            )

        @get:Throws(ToxFriendQueryException::class)
        public override val typing: Boolean
            get() = getTyping(
                this@Tox.handle,
                this@FriendImpl.handle,
            )

        @Throws(ToxFriendGetPublicKeyException::class)
        private external fun getPublicKey(toxHandle: Long, friendImplHandle: Int): Array<Byte>

        @Throws(ToxFriendGetLastOnlineException::class)
        private external fun getLastOnline(toxHandle: Long, friendImplHandle: Int): Long

        @Throws(ToxFriendQueryException::class)
        private external fun getName(toxHandle: Long, friendImplHandle: Int): String

        @Throws(ToxFriendQueryException::class)
        private external fun getStatusMessage(toxHandle: Long, friendImplHandle: Int): String

        @Throws(ToxFriendQueryException::class)
        private external fun getStatus(toxHandle: Long, friendImplHandle: Int): ToxUserStatus

        @Throws(ToxFriendQueryException::class)
        private external fun getConnectionStatus(toxHandle: Long, friendImplHandle: Int):
                ToxConnection

        @Throws(ToxFriendQueryException::class)
        private external fun getTyping(toxHandle: Long, friendImplHandle: Int): Boolean

        @Throws(ToxFriendDeleteException::class)
        public override fun delete(): Unit = 
            delete(
                this@Tox.handle,
                this@FriendImpl.handle,
            )

        @Throws(ToxFriendDeleteException::class)
        private external fun delete(toxHandle: Long, friendImplHandle: Int): Unit

        public override fun exists(): Boolean = 
            exists(
                this@Tox.handle,
                this@FriendImpl.handle,
            )

        private external fun exists(toxHandle: Long, friendImplHandle: Int): Boolean

        @Throws(ToxSetTypingException::class)
        public override fun selfSetTyping(typing: Boolean): Unit = 
            selfSetTyping(
                this@Tox.handle,
                this@FriendImpl.handle,
                typing,
            )

        @Throws(ToxSetTypingException::class)
        private external fun selfSetTyping(
            toxHandle: Long,
            friendImplHandle: Int,
            typing: Boolean,
        ): Unit

        @Throws(ToxFriendSendMessageException::class)
        public override fun sendMessage(type: ToxMessageType, message: String): Int = 
            sendMessage(
                this@Tox.handle,
                this@FriendImpl.handle,
                type,
                message,
            )

        @Throws(ToxFriendSendMessageException::class)
        private external fun sendMessage(
            toxHandle: Long,
            friendImplHandle: Int,
            type: ToxMessageType,
            message: String,
        ): Int

        @Throws(ToxFileSendException::class)
        public override fun sendFile(
            kind: Int,
            fileSize: Long,
            fileId: Array<Byte>,
            filename: String,
        ): File = 
            sendFile(
                this@Tox.handle,
                this@FriendImpl.handle,
                kind,
                fileSize,
                fileId,
                filename,
            )

        @Throws(ToxFileSendException::class)
        private external fun sendFile(
            toxHandle: Long,
            friendImplHandle: Int,
            kind: Int,
            fileSize: Long,
            fileId: Array<Byte>,
            filename: String,
        ): File

        @Throws(ToxConferenceInviteException::class)
        public override fun conferenceInvite(conference: Conference): Unit = 
            conferenceInvite(
                this@Tox.handle,
                this@FriendImpl.handle,
                (conference as ConferenceImpl).handle,
            )

        @Throws(ToxConferenceInviteException::class)
        private external fun conferenceInvite(
            toxHandle: Long,
            friendImplHandle: Int,
            conferenceImplHandle: Int,
        ): Unit

        @Throws(ToxConferenceJoinException::class)
        public override fun conferenceJoin(cookie: Array<Byte>): Conference = 
            conferenceJoin(
                this@Tox.handle,
                this@FriendImpl.handle,
                cookie,
            )

        @Throws(ToxConferenceJoinException::class)
        private external fun conferenceJoin(
            toxHandle: Long,
            friendImplHandle: Int,
            cookie: Array<Byte>,
        ): Conference

        @Throws(ToxFriendCustomPacketException::class)
        public override fun sendLossyPacket(`data`: Array<Byte>): Unit = 
            sendLossyPacket(
                this@Tox.handle,
                this@FriendImpl.handle,
                data,
            )

        @Throws(ToxFriendCustomPacketException::class)
        private external fun sendLossyPacket(
            toxHandle: Long,
            friendImplHandle: Int,
            `data`: Array<Byte>,
        ): Unit

        @Throws(ToxFriendCustomPacketException::class)
        public override fun sendLosslessPacket(`data`: Array<Byte>): Unit = 
            sendLosslessPacket(
                this@Tox.handle,
                this@FriendImpl.handle,
                data,
            )

        @Throws(ToxFriendCustomPacketException::class)
        private external fun sendLosslessPacket(
            toxHandle: Long,
            friendImplHandle: Int,
            `data`: Array<Byte>,
        ): Unit

        internal inner class FileImpl private constructor(
            internal val handle: Int,
        ) : File {
            @get:Throws(ToxFileGetException::class)
            public override val id: Array<Byte>
                get() = getId(
                    this@Tox.handle,
                    this@FriendImpl.handle,
                    this@FileImpl.handle,
                )

            @Throws(ToxFileGetException::class)
            private external fun getId(
                toxHandle: Long,
                friendImplHandle: Int,
                fileImplHandle: Int,
            ): Array<Byte>

            @Throws(ToxFileControlException::class)
            public override fun control(control: ToxFileControl): Unit = 
                control(
                    this@Tox.handle,
                    this@FriendImpl.handle,
                    this@FileImpl.handle,
                    control,
                )

            @Throws(ToxFileControlException::class)
            private external fun control(
                toxHandle: Long,
                friendImplHandle: Int,
                fileImplHandle: Int,
                control: ToxFileControl,
            ): Unit

            @Throws(ToxFileSeekException::class)
            public override fun seek(position: Long): Unit = 
                seek(
                    this@Tox.handle,
                    this@FriendImpl.handle,
                    this@FileImpl.handle,
                    position,
                )

            @Throws(ToxFileSeekException::class)
            private external fun seek(
                toxHandle: Long,
                friendImplHandle: Int,
                fileImplHandle: Int,
                position: Long,
            ): Unit

            @Throws(ToxFileSendChunkException::class)
            public override fun sendChunk(position: Long, `data`: Array<Byte>): Unit = 
                sendChunk(
                    this@Tox.handle,
                    this@FriendImpl.handle,
                    this@FileImpl.handle,
                    position,
                    data,
                )

            @Throws(ToxFileSendChunkException::class)
            private external fun sendChunk(
                toxHandle: Long,
                friendImplHandle: Int,
                fileImplHandle: Int,
                position: Long,
                `data`: Array<Byte>,
            ): Unit
        }
    }

    internal inner class ConferenceImpl private constructor(
        internal val handle: Int,
    ) : Conference {
        @get:Throws(ToxConferenceTitleException::class)
        @set:Throws(ToxConferenceTitleException::class)
        public override var title: String
            get() = getTitle(
                this@Tox.handle,
                this@ConferenceImpl.handle,
            )
            set(title) = setTitle(
                this@Tox.handle,
                this@ConferenceImpl.handle,
                title,
            )

        @get:Throws(ToxConferenceGetTypeException::class)
        public override val type: ToxConferenceType
            get() = getType(
                this@Tox.handle,
                this@ConferenceImpl.handle,
            )

        public override val id: Array<Byte>
            get() = getId(
                this@Tox.handle,
                this@ConferenceImpl.handle,
            )

        public override val uid: Array<Byte>
            get() = getUid(
                this@Tox.handle,
                this@ConferenceImpl.handle,
            )

        @Throws(ToxConferenceTitleException::class)
        private external fun getTitle(toxHandle: Long, conferenceImplHandle: Int): String

        @Throws(ToxConferenceTitleException::class)
        private external fun setTitle(
            toxHandle: Long,
            conferenceImplHandle: Int,
            title: String,
        ): Unit

        @Throws(ToxConferenceGetTypeException::class)
        private external fun getType(toxHandle: Long, conferenceImplHandle: Int): ToxConferenceType

        private external fun getId(toxHandle: Long, conferenceImplHandle: Int): Array<Byte>

        private external fun getUid(toxHandle: Long, conferenceImplHandle: Int): Array<Byte>

        @Throws(ToxConferenceDeleteException::class)
        public override fun delete(): Unit = 
            delete(
                this@Tox.handle,
                this@ConferenceImpl.handle,
            )

        @Throws(ToxConferenceDeleteException::class)
        private external fun delete(toxHandle: Long, conferenceImplHandle: Int): Unit

        @Throws(ToxConferencePeerQueryException::class)
        public override fun peerCount(): Int = 
            peerCount(
                this@Tox.handle,
                this@ConferenceImpl.handle,
            )

        @Throws(ToxConferencePeerQueryException::class)
        private external fun peerCount(toxHandle: Long, conferenceImplHandle: Int): Int

        @Throws(ToxConferencePeerQueryException::class)
        public override fun peerIsOurs(peer: Peer): Unit = 
            peerIsOurs(
                this@Tox.handle,
                this@ConferenceImpl.handle,
                (peer as PeerImpl).handle,
            )

        @Throws(ToxConferencePeerQueryException::class)
        private external fun peerIsOurs(
            toxHandle: Long,
            conferenceImplHandle: Int,
            peerImplHandle: Int,
        ): Unit

        @Throws(ToxConferencePeerQueryException::class)
        public override fun offlinePeerCount(): Int = 
            offlinePeerCount(
                this@Tox.handle,
                this@ConferenceImpl.handle,
            )

        @Throws(ToxConferencePeerQueryException::class)
        private external fun offlinePeerCount(toxHandle: Long, conferenceImplHandle: Int): Int

        @Throws(ToxConferenceSetMaxOfflineException::class)
        public override fun setMaxOffline(maxOfflinePeers: Int): Unit = 
            setMaxOffline(
                this@Tox.handle,
                this@ConferenceImpl.handle,
                maxOfflinePeers,
            )

        @Throws(ToxConferenceSetMaxOfflineException::class)
        private external fun setMaxOffline(
            toxHandle: Long,
            conferenceImplHandle: Int,
            maxOfflinePeers: Int,
        ): Unit

        @Throws(ToxConferenceSendMessageException::class)
        public override fun sendMessage(type: ToxMessageType, message: String): Unit = 
            sendMessage(
                this@Tox.handle,
                this@ConferenceImpl.handle,
                type,
                message,
            )

        @Throws(ToxConferenceSendMessageException::class)
        private external fun sendMessage(
            toxHandle: Long,
            conferenceImplHandle: Int,
            type: ToxMessageType,
            message: String,
        ): Unit

        internal inner class PeerImpl private constructor(
            internal val handle: Int,
        ) : Peer {
            @get:Throws(ToxConferencePeerQueryException::class)
            public override val name: String
                get() = getName(
                    this@Tox.handle,
                    this@ConferenceImpl.handle,
                    this@PeerImpl.handle,
                )

            @get:Throws(ToxConferencePeerQueryException::class)
            public override val publicKey: Array<Byte>
                get() = getPublicKey(
                    this@Tox.handle,
                    this@ConferenceImpl.handle,
                    this@PeerImpl.handle,
                )

            @Throws(ToxConferencePeerQueryException::class)
            private external fun getName(
                toxHandle: Long,
                conferenceImplHandle: Int,
                peerImplHandle: Int,
            ): String

            @Throws(ToxConferencePeerQueryException::class)
            private external fun getPublicKey(
                toxHandle: Long,
                conferenceImplHandle: Int,
                peerImplHandle: Int,
            ): Array<Byte>
        }

        internal inner class OfflinePeerImpl private constructor(
            internal val handle: Int,
        ) : OfflinePeer {
            @get:Throws(ToxConferencePeerQueryException::class)
            public override val name: String
                get() = getName(
                    this@Tox.handle,
                    this@ConferenceImpl.handle,
                    this@OfflinePeerImpl.handle,
                )

            @get:Throws(ToxConferencePeerQueryException::class)
            public override val publicKey: Array<Byte>
                get() = getPublicKey(
                    this@Tox.handle,
                    this@ConferenceImpl.handle,
                    this@OfflinePeerImpl.handle,
                )

            @get:Throws(ToxConferencePeerQueryException::class)
            public override val lastActive: Long
                get() = getLastActive(
                    this@Tox.handle,
                    this@ConferenceImpl.handle,
                    this@OfflinePeerImpl.handle,
                )

            @Throws(ToxConferencePeerQueryException::class)
            private external fun getName(
                toxHandle: Long,
                conferenceImplHandle: Int,
                offlinePeerImplHandle: Int,
            ): String

            @Throws(ToxConferencePeerQueryException::class)
            private external fun getPublicKey(
                toxHandle: Long,
                conferenceImplHandle: Int,
                offlinePeerImplHandle: Int,
            ): Array<Byte>

            @Throws(ToxConferencePeerQueryException::class)
            private external fun getLastActive(
                toxHandle: Long,
                conferenceImplHandle: Int,
                offlinePeerImplHandle: Int,
            ): Long
        }
    }

    public companion object {
        @JvmStatic
        public val versionMajor: Int
            external get

        @JvmStatic
        public val versionMinor: Int
            external get

        @JvmStatic
        public val versionPatch: Int
            external get

        @JvmStatic
        public val publicKeySize: Int
            external get

        @JvmStatic
        public val secretKeySize: Int
            external get

        @JvmStatic
        public val conferenceUidSize: Int
            external get

        @JvmStatic
        public val conferenceIdSize: Int
            external get

        @JvmStatic
        public val nospamSize: Int
            external get

        @JvmStatic
        public val addressSize: Int
            external get

        @JvmStatic
        public val maxNameLength: Int
            external get

        @JvmStatic
        public val maxStatusMessageLength: Int
            external get

        @JvmStatic
        public val maxFriendRequestLength: Int
            external get

        @JvmStatic
        public val maxMessageLength: Int
            external get

        @JvmStatic
        public val maxCustomPacketSize: Int
            external get

        @JvmStatic
        public val hashLength: Int
            external get

        @JvmStatic
        public val fileIdLength: Int
            external get

        @JvmStatic
        public val maxFilenameLength: Int
            external get

        @JvmStatic
        public val maxHostnameLength: Int
            external get

        @JvmStatic
        @Throws(ToxNewException::class)
        private external fun allocateNative(options: Long): Long

        @JvmStatic
        private external fun deallocateNative(handle: Long): Unit

        @JvmStatic
        public external fun versionIsCompatible(
            major: Int,
            minor: Int,
            patch: Int,
        ): Boolean

        @JvmStatic
        public external fun hash(`data`: Array<Byte>): Array<Byte>
    }
}
