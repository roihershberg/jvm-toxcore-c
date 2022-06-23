package ltd.toktok.tox4j

import kotlin.Array
import kotlin.Byte
import kotlin.String

public sealed interface Peer {
    public val name: String

    public val publicKey: Array<Byte>
}
