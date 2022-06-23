package ltd.toktok.tox4j.gen.ir

import com.fasterxml.jackson.annotation.JsonProperty

data class IRReturnType(
    val type: IRType,
    val replaced: IRType?,
    @JsonProperty("param_index") val paramIndex: Int?,
)
