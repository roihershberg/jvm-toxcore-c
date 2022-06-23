package ltd.toktok.tox4j

import kotlin.Array
import kotlin.Byte
import kotlin.Long
import kotlin.String

public sealed interface OfflinePeer {
    public val name: String

    public val publicKey: Array<Byte>

    public val lastActive: Long
}
