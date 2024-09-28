package processor

import de.niklaskerkhoff.processor.IllegalTokenException
import de.niklaskerkhoff.processor.Lexer
import de.niklaskerkhoff.processor.Parser
import de.niklaskerkhoff.processor.Token
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParserTest {


    @Test
    fun `it should parse numbers`() {

        val parsers = listOf(
            "1",
            "9876543201",
            "1.2",
            "43201.98765",
            "-1",
            "-9876543201",
            "-1.2",
            "-43201.98765",
        ).map { Parser(Lexer(it)) }

        val expected = listOf(
            1.0,
            9876543201.0,
            1.2,
            43201.98765,
            -1.0,
            -9876543201.0,
            -1.2,
            -43201.98765,
        )

        parsers.forEachIndexed { index, parser ->
            assertEquals(expected[index], parser.parse().eval())
        }
    }

    @Test
    fun `it should parse simple expressions`() {
        val parsers = listOf(
            "1.5+2.5",
            "1.5-2.5",
            "1.5*2.5",
            "1.5/2.5",
            "-1.5+2.5",
            "-1.5-2.5",
            "-1.5*2.5",
            "-1.5/2.5",
            "1.5+-2.5",
            "1.5--2.5",
            "1.5*-2.5",
            "1.5/-2.5",
            "-1.5+-2.5",
            "-1.5--2.5",
            "-1.5*-2.5",
            "-1.5/-2.5",
        ).map { Parser(Lexer(it)) }

        val expected = listOf(
            1.5 + 2.5,
            1.5 - 2.5,
            1.5 * 2.5,
            1.5 / 2.5,
            -1.5 + 2.5,
            -1.5 - 2.5,
            -1.5 * 2.5,
            -1.5 / 2.5,
            1.5 + -2.5,
            1.5 - -2.5,
            1.5 * -2.5,
            1.5 / -2.5,
            -1.5 + -2.5,
            -1.5 - -2.5,
            -1.5 * -2.5,
            -1.5 / -2.5,
        )

        parsers.forEachIndexed { index, parser ->
            assertEquals(expected[index], parser.parse().eval())
        }
    }

    @Test
    fun `it should parse complex expressions`() {
        val parsers = listOf(
            "2+3*4",
            "2*3+4",
            "(2+3,1)*(4+5)",
            "(2-3)/(4-5)",
            "3*(-2.3/((2+4)))",
        ).map { Parser(Lexer(it)) }

        val expected = listOf(
            2 + 3 * 4,
            2 * 3 + 4,
            (2 + 3.1) * (4 + 5),
            (2 - 3) / (4 - 5.0),
            3 * (-2.3 / ((2.0 + 4))),
        ).map(Number::toDouble)

        parsers.forEachIndexed { index, parser ->
            assertEquals(expected[index], parser.parse().eval())
        }
    }

    @Test
    fun `it should fail on illegal token`() {
        val parsers = listOf(
            "2+",
            "2(",
        ).map { Parser(Lexer(it)) }

        val expected = listOf(
            Token.EOF,
            Token.LP,
        ).map(Enum<Token>::toString)

        parsers.forEachIndexed { index, parser ->
            val exception = assertThrows<IllegalTokenException> { parser.parse().eval() }
            assertEquals(expected[index], exception.message)
        }
    }
}
