package ltd.toktok.tox4j.gen.ir

import com.fasterxml.jackson.annotation.JsonProperty

data class IRProperty(
    val name: String,
    @JsonProperty("is_static") val isStatic: Boolean,
    val getter: IRFunction,
    val setter: IRFunction?,
)
