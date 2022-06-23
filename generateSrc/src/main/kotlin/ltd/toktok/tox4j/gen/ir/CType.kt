package ltd.toktok.tox4j.gen.ir

import com.fasterxml.jackson.annotation.JsonProperty

data class CType(
    val name: String,
    @JsonProperty("is_pointer") val isPointer: Boolean,
)
