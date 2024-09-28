package de.niklaskerkhoff.processor

import de.niklaskerkhoff.exp.*

class Parser(
    private val lexer: Lexer,
) {

    fun parse() = parseS()

    private fun parseS(): Exp {
        val m = parseM()
        return parseS1(m)
    }

    private fun parseS1(left: Exp): Exp =
        when (lexer.currentToken) {
            Token.PLUS -> {
                expect(Token.PLUS)
                val m = parseM()
                val exp = SumExp(left, m)
                parseS1(exp)
            }

            Token.MINUS -> {
                expect(Token.MINUS)
                val m = parseM()
                val exp = DiffExp(left, m)
                parseS1(exp)
            }

            Token.RP, Token.EOF -> left

            else -> {
                throw IllegalTokenException(lexer.currentToken)
            }
        }

    private fun parseM(): Exp {
        val a = parseA()
        return parseM1(a)
    }

    private fun parseM1(left: Exp): Exp =
        when (lexer.currentToken) {
            Token.TIMES -> {
                expect(Token.TIMES)
                val a = parseA()
                val exp = ProdExp(left, a)
                parseM1(exp)
            }

            Token.REAL_DIV -> {
                expect(Token.REAL_DIV)
                val a = parseA()
                val exp = QuotExp(left, a)
                parseM1(exp)
            }

            Token.PLUS, Token.MINUS, Token.RP, Token.EOF -> left

            else -> {
                throw IllegalTokenException(lexer.currentToken)
            }
        }

    private fun parseA(): Exp =
        when (lexer.currentToken) {
            Token.NUM -> {
                val intPart = expect(Token.NUM)
                parseA1(intPart)
            }

            Token.MINUS -> {
                expect(Token.MINUS)
                val intPart = expect(Token.NUM)
                parseA1("-$intPart")
            }

            Token.LP -> {
                expect(Token.LP)
                val exp = parseS()
                expect(Token.RP)
                exp
            }

            else -> {
                throw IllegalTokenException(lexer.currentToken)
            }
        }

    private fun parseA1(intPart: String): Exp =
        when (lexer.currentToken) {
            Token.DOT -> {
                expect(Token.DOT)
                val doublePart = expect(Token.NUM)
                DoubleExp(intPart, doublePart)
            }

            Token.TIMES, Token.REAL_DIV, Token.PLUS, Token.MINUS, Token.RP, Token.EOF -> IntExp(intPart)

            else -> {
                throw IllegalTokenException(lexer.currentToken)
            }
        }


    private fun expect(token: Token): String {
        if (lexer.currentToken != token) throw IllegalTokenException(lexer.currentToken)
        val value = lexer.currentValue
        lexer.lex()
        return value
    }
}
