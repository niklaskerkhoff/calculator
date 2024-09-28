package de.niklaskerkhoff.processor

enum class Token(
    val pattern: Regex
) {
    NUM(Regex("\\d+")),
    LP(Regex("\\(")),
    RP(Regex("\\)")),
    PLUS(Regex("\\+")),
    MINUS(Regex("-")),
    TIMES(Regex("\\*")),
    REAL_DIV(Regex("/")),
    DOT(Regex("[.,]")),
    EOF(Regex(""));
}
