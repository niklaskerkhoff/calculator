package de.niklaskerkhoff

import de.niklaskerkhoff.processor.Lexer
import de.niklaskerkhoff.processor.Parser
import de.niklaskerkhoff.utils.roundTo

fun main() {
    while (true) {
        print("> ")
        println("  = ${getResult(readln()).roundTo(8)}")
    }
}

private fun getResult(input: String) = Parser(Lexer(input)).parse().eval()
