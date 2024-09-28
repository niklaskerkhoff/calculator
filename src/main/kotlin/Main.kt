package de.niklaskerkhoff

import de.niklaskerkhoff.processor.Lexer
import de.niklaskerkhoff.processor.Parser

fun main() {
    while (true) println(getResult(readln()))
}

private fun getResult(input: String) = Parser(Lexer(input)).parse().eval()
