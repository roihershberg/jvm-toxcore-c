package ltd.toktok.tox4j.gen.ir

import com.fasterxml.jackson.annotation.JsonProperty

data class Root(
    @JsonProperty("ir_version") val irVersion: String,
    @JsonProperty("tox_version") val toxVersion: String,
    val enums: List<IREnum>,
    val exceptions: List<IRException>,
    val classes: List<IRClass>,
)
