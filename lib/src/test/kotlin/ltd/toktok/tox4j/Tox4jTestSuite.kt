package ltd.toktok.tox4j

import ltd.toktok.tox4j.callbacks.ToxConferenceConnectedCb
import ltd.toktok.tox4j.callbacks.ToxConferenceMessageCb
import ltd.toktok.tox4j.callbacks.ToxConferencePeerListChangedCb
import kotlin.test.Test
import kotlin.test.assertTrue

public class Tox4jTestSuite {
    @Test
    public fun someLibraryMethodReturnsTrue() {
        val classUnderTest = Library()
        assertTrue(classUnderTest.someLibraryMethod(), "someLibraryMethod should return 'true'")
    }
}
