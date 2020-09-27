package id.ac.ui.cs.davenathanael.newspaper

import org.junit.Assert.assertEquals
import org.junit.Test

class HelloCounterTest {
    private val counter = HelloCounter()

    @Test
    fun increment_thenSuccess() {
        assertEquals(0, counter.count)

        var testCount = 0

        counter.increment { testCount += it }
        assertEquals(1, counter.count)
        assertEquals(1, testCount)

        counter.increment { testCount += it }
        assertEquals(2, counter.count)
        assertEquals(3, testCount)
    }

}