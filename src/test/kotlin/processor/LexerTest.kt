package processor

import de.niklaskerkhoff.processor.IllegalCharacterException
import de.niklaskerkhoff.processor.Lexer
import de.niklaskerkhoff.processor.Token
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LexerTest {

    private val tokens = mapOf(
        "+" to Token.PLUS,
        "-" to Token.MINUS,
        "*" to Token.TIMES,
        "/" to Token.REAL_DIV,
        "(" to Token.LP,
        ")" to Token.RP,
        "." to Token.DOT,
        "," to Token.DOT,
    )


    @Test
    fun `it should recognize numbers`() {
        val lexer = Lexer("123")
        assertEquals(Token.NUM, lexer.currentToken)
        assertEquals("123", lexer.currentValue)
        lexer.lex()

        assertEquals(Token.EOF, lexer.currentToken)
        assertEquals("", lexer.currentValue)
    }

    @Test
    fun `it should recognize tokens`() {
        val input = "++**//--(())..,,"
        val inputTokens = input.map { tokens[it.toString()] }

        val lexer = Lexer(input)

        for (i in input.indices) {
            assertEquals(inputTokens[i], lexer.currentToken)
            assertEquals(input[i].toString(), lexer.currentValue)
            lexer.lex()
        }

        assertEquals(Token.EOF, lexer.currentToken)
        assertEquals("", lexer.currentValue)
    }

    @Test
    fun `it should recognize terms`() {
        val lexer = Lexer("04750+-12.23423")

        assertEquals(Token.NUM, lexer.currentToken)
        assertEquals("04750", lexer.currentValue)
        lexer.lex()

        assertEquals(Token.PLUS, lexer.currentToken)
        assertEquals("+", lexer.currentValue)
        lexer.lex()

        assertEquals(Token.MINUS, lexer.currentToken)
        assertEquals("-", lexer.currentValue)
        lexer.lex()

        assertEquals(Token.NUM, lexer.currentToken)
        assertEquals("12", lexer.currentValue)
        lexer.lex()

        assertEquals(Token.DOT, lexer.currentToken)
        assertEquals(".", lexer.currentValue)
        lexer.lex()

        assertEquals(Token.NUM, lexer.currentToken)
        assertEquals("23423", lexer.currentValue)
        lexer.lex()

        assertEquals(Token.EOF, lexer.currentToken)
        assertEquals("", lexer.currentValue)
    }

    @Test
    fun `it should fail on illegal character`() {
        val lexer = Lexer("123*#")
        lexer.lex()
        val exception = assertThrows<IllegalCharacterException> { lexer.lex() }
        assertEquals("#", exception.message)
    }
}
