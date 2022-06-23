package ltd.toktok.tox4j.gen

import kotlin.test.Test

class Tox4jGenTestSuite {
    @Test
    fun testCodeBlock() {
        val codeBlock = buildCodeBlock {
            +"#include <stdio.h>"
            +""
            block("int main()") {
                +"int i = 0;"

                block("if (i == 0)") {
                    +"puts(\"Yo\");"
                }

                +"return i;"
            }
        }

        println(codeBlock.toString())
    }
}
