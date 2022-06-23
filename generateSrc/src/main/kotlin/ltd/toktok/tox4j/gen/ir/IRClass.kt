package ltd.toktok.tox4j.gen.ir

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo

data class IRClass(
    val name: String,
    @JsonProperty("is_callback") val isCallback: Boolean,
    val handle: IRHandle?,
    @JsonProperty("default_init") val defaultInit: IRFunction?,
    val properties: List<IRProperty>,
    val functions: List<IRFunction>,
    @JsonProperty("inner_classes") val innerClasses: List<IRClass>,
)

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "object_name",
)
@JsonSubTypes(
    value = [
        Type(value = IRNativeHandle::class, name = "IRNativeHandle"),
        Type(value = IRNumberHandle::class, name = "IRNumberHandle"),
    ]
)
sealed interface IRHandle

data class IRNativeHandle(
    @JsonProperty("alloc_func") val allocFunc: IRFunction?,
    @JsonProperty("dealloc_func") val deallocFunc: IRFunction,
) : IRHandle

data class IRNumberHandle(val type: IRType) : IRHandle
