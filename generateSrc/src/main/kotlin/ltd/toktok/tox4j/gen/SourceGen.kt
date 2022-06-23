package ltd.toktok.tox4j.gen

import java.io.File

fun main(args: Array<String>) {
    val irFile = File("../tox_oop_api_ir.json")
    KotlinAPIGen.generate(IRParser.parse(irFile.readText()), File("../lib/src/main/kotlin"))
}
