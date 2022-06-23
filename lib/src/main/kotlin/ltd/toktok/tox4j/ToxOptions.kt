package ltd.toktok.tox4j

import kotlin.Array
import kotlin.Boolean
import kotlin.Byte
import kotlin.Long
import kotlin.Short
import kotlin.String
import kotlin.Unit
import kotlin.jvm.JvmStatic
import kotlin.jvm.Throws
import ltd.toktok.tox4j.callbacks.ToxLogCb
import ltd.toktok.tox4j.errors.ToxOptionsNewException

public class ToxOptions {
    internal val handle: Long = allocateNative()

    public var ipv6Enabled: Boolean
        get() = getIpv6Enabled(
            this@ToxOptions.handle,
        )
        set(ipv6Enabled) = setIpv6Enabled(
            this@ToxOptions.handle,
            ipv6Enabled,
        )

    public var udpEnabled: Boolean
        get() = getUdpEnabled(
            this@ToxOptions.handle,
        )
        set(udpEnabled) = setUdpEnabled(
            this@ToxOptions.handle,
            udpEnabled,
        )

    public var localDiscoveryEnabled: Boolean
        get() = getLocalDiscoveryEnabled(
            this@ToxOptions.handle,
        )
        set(localDiscoveryEnabled) = setLocalDiscoveryEnabled(
            this@ToxOptions.handle,
            localDiscoveryEnabled,
        )

    public var dhtAnnouncementsEnabled: Boolean
        get() = getDhtAnnouncementsEnabled(
            this@ToxOptions.handle,
        )
        set(dhtAnnouncementsEnabled) = setDhtAnnouncementsEnabled(
            this@ToxOptions.handle,
            dhtAnnouncementsEnabled,
        )

    public var proxyType: ToxProxyType
        get() = getProxyType(
            this@ToxOptions.handle,
        )
        set(type) = setProxyType(
            this@ToxOptions.handle,
            type,
        )

    public var proxyHost: String
        get() = getProxyHost(
            this@ToxOptions.handle,
        )
        set(host) = setProxyHost(
            this@ToxOptions.handle,
            host,
        )

    public var proxyPort: Short
        get() = getProxyPort(
            this@ToxOptions.handle,
        )
        set(port) = setProxyPort(
            this@ToxOptions.handle,
            port,
        )

    public var startPort: Short
        get() = getStartPort(
            this@ToxOptions.handle,
        )
        set(startPort) = setStartPort(
            this@ToxOptions.handle,
            startPort,
        )

    public var endPort: Short
        get() = getEndPort(
            this@ToxOptions.handle,
        )
        set(endPort) = setEndPort(
            this@ToxOptions.handle,
            endPort,
        )

    public var tcpPort: Short
        get() = getTcpPort(
            this@ToxOptions.handle,
        )
        set(tcpPort) = setTcpPort(
            this@ToxOptions.handle,
            tcpPort,
        )

    public var holePunchingEnabled: Boolean
        get() = getHolePunchingEnabled(
            this@ToxOptions.handle,
        )
        set(holePunchingEnabled) = setHolePunchingEnabled(
            this@ToxOptions.handle,
            holePunchingEnabled,
        )

    public var savedataType: ToxSavedataType
        get() = getSavedataType(
            this@ToxOptions.handle,
        )
        set(type) = setSavedataType(
            this@ToxOptions.handle,
            type,
        )

    public var savedataData: Array<Byte>
        get() = getSavedataData(
            this@ToxOptions.handle,
        )
        set(`data`) = setSavedataData(
            this@ToxOptions.handle,
            data,
        )

    public var logCallback: ToxLogCb
        get() = getLogCallback(
            this@ToxOptions.handle,
        )
        set(callback) = setLogCallback(
            this@ToxOptions.handle,
            callback,
        )

    public var logUserData: Unit
        get() = getLogUserData(
            this@ToxOptions.handle,
        )
        set(userData) = setLogUserData(
            this@ToxOptions.handle,
            userData,
        )

    public var experimentalThreadSafety: Boolean
        get() = getExperimentalThreadSafety(
            this@ToxOptions.handle,
        )
        set(experimentalThreadSafety) = setExperimentalThreadSafety(
            this@ToxOptions.handle,
            experimentalThreadSafety,
        )

    private external fun getIpv6Enabled(toxOptionsHandle: Long): Boolean

    private external fun setIpv6Enabled(toxOptionsHandle: Long, ipv6Enabled: Boolean): Unit

    private external fun getUdpEnabled(toxOptionsHandle: Long): Boolean

    private external fun setUdpEnabled(toxOptionsHandle: Long, udpEnabled: Boolean): Unit

    private external fun getLocalDiscoveryEnabled(toxOptionsHandle: Long): Boolean

    private external fun setLocalDiscoveryEnabled(toxOptionsHandle: Long,
            localDiscoveryEnabled: Boolean): Unit

    private external fun getDhtAnnouncementsEnabled(toxOptionsHandle: Long): Boolean

    private external fun setDhtAnnouncementsEnabled(toxOptionsHandle: Long,
            dhtAnnouncementsEnabled: Boolean): Unit

    private external fun getProxyType(toxOptionsHandle: Long): ToxProxyType

    private external fun setProxyType(toxOptionsHandle: Long, type: ToxProxyType): Unit

    private external fun getProxyHost(toxOptionsHandle: Long): String

    private external fun setProxyHost(toxOptionsHandle: Long, host: String): Unit

    private external fun getProxyPort(toxOptionsHandle: Long): Short

    private external fun setProxyPort(toxOptionsHandle: Long, port: Short): Unit

    private external fun getStartPort(toxOptionsHandle: Long): Short

    private external fun setStartPort(toxOptionsHandle: Long, startPort: Short): Unit

    private external fun getEndPort(toxOptionsHandle: Long): Short

    private external fun setEndPort(toxOptionsHandle: Long, endPort: Short): Unit

    private external fun getTcpPort(toxOptionsHandle: Long): Short

    private external fun setTcpPort(toxOptionsHandle: Long, tcpPort: Short): Unit

    private external fun getHolePunchingEnabled(toxOptionsHandle: Long): Boolean

    private external fun setHolePunchingEnabled(toxOptionsHandle: Long,
            holePunchingEnabled: Boolean): Unit

    private external fun getSavedataType(toxOptionsHandle: Long): ToxSavedataType

    private external fun setSavedataType(toxOptionsHandle: Long, type: ToxSavedataType): Unit

    private external fun getSavedataData(toxOptionsHandle: Long): Array<Byte>

    private external fun setSavedataData(toxOptionsHandle: Long, `data`: Array<Byte>): Unit

    private external fun getLogCallback(toxOptionsHandle: Long): ToxLogCb

    private external fun setLogCallback(toxOptionsHandle: Long, callback: ToxLogCb): Unit

    private external fun getLogUserData(toxOptionsHandle: Long): Unit

    private external fun setLogUserData(toxOptionsHandle: Long, userData: Unit): Unit

    private external fun getExperimentalThreadSafety(toxOptionsHandle: Long): Boolean

    private external fun setExperimentalThreadSafety(toxOptionsHandle: Long,
            experimentalThreadSafety: Boolean): Unit

    public companion object {
        @JvmStatic
        @Throws(ToxOptionsNewException::class)
        private external fun allocateNative(): Long

        @JvmStatic
        private external fun deallocateNative(handle: Long): Unit
    }
}
