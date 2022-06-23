package ltd.toktok.tox4j.gen.ir

import com.fasterxml.jackson.annotation.JsonProperty

data class IRType(
    val name: String,
    val mutable: Boolean,
    @JsonProperty("is_array") val isArray: Boolean,
    @JsonProperty("acts_as_string") val actsAsString: Boolean,
    @JsonProperty("contains_number_handle") val containsNumberHandle: Boolean,
    val ctype: CType,
    @JsonProperty("get_size_func") val getSizeFunc: IRFunction?,
    @JsonProperty("set_size_func") val setSizeFunc: IRFunction?,
)
