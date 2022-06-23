package ltd.toktok.tox4j.gen

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import ltd.toktok.tox4j.gen.ir.Root

object IRParser {
    fun parse(jsonString: String): Root {
        val mapper = jsonMapper {
            addModule(kotlinModule())
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        }

        return mapper.readValue(jsonString, Root::class.java)
    }
}