package ltd.toktok.tox4j.gen.ir

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

data class IRFunction(
    val name: String,
    val cname: String,
    @JsonProperty("return_type") val returnType: IRReturnType,
    @JsonProperty("replaced_return_type") val replacedReturnType: IRType?,
    val throws: String?,
    @JsonProperty("is_static") val isStatic: Boolean,
    val params: List<IRFuncArg>,
)

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "object_name",
)
@JsonSubTypes(
    value = [
        JsonSubTypes.Type(value = IRBufferWrapper::class, name = "IRBufferWrapper"),
        JsonSubTypes.Type(value = IRParam::class, name = "IRParam"),
    ]
)
sealed interface IRFuncArg

data class IRBufferWrapper(
    @JsonProperty("buffer_param") val bufferParam: IRParam,
    @JsonProperty("length_param") val lengthParam: IRParam,
) : IRFuncArg

data class IRParam(
    val name: String,
    val type: IRType,
    @JsonProperty("replaced_type") val replacedType: IRType?,
) : IRFuncArg
