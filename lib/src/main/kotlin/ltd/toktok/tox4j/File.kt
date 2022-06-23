package ltd.toktok.tox4j

import kotlin.Array
import kotlin.Byte
import kotlin.Long
import kotlin.Unit
import kotlin.jvm.Throws
import ltd.toktok.tox4j.errors.ToxFileControlException
import ltd.toktok.tox4j.errors.ToxFileSeekException
import ltd.toktok.tox4j.errors.ToxFileSendChunkException

public sealed interface File {
    public val id: Array<Byte>

    @Throws(ToxFileControlException::class)
    public fun control(control: ToxFileControl): Unit

    @Throws(ToxFileSeekException::class)
    public fun seek(position: Long): Unit

    @Throws(ToxFileSendChunkException::class)
    public fun sendChunk(position: Long, `data`: Array<Byte>): Unit
}
