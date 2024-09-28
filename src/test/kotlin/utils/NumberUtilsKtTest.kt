package utils

import de.niklaskerkhoff.utils.roundTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NumberUtilsKtTest {
    @Test
    fun `it should round the double`() {
        val input = 12.123456789
        assertEquals(12.0, input.roundTo(0))
        assertEquals(12.1, input.roundTo(1))
        assertEquals(12.1235, input.roundTo(4))
        assertEquals(12.12345679, input.roundTo(8))
    }

    @Test
    fun `it should not change when passed decimals to large`() {
        val input = 12.123456789
        assertEquals(input, input.roundTo(16))
        assertEquals(input, input.roundTo(20))
    }

    @Test
    fun `it should not change when double to short`() {
        val input = 12.123456789
        assertEquals(input, input.roundTo(9))
        assertEquals(input, input.roundTo(10))
        assertEquals(input, input.roundTo(15))
    }
}
