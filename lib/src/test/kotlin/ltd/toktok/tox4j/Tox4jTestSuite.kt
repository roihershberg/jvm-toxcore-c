package ltd.toktok.tox4j

import kotlin.test.Test

class Tox4jTestSuite {

    init {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "DEBUG")
    }

    @Test fun someLibraryMethodReturnsTrue() {
        println("${Tox.versionMajor}.${Tox.versionMinor}.${Tox.versionPatch}")
        println("${Tox.maxHostnameLength}")
    }
}
