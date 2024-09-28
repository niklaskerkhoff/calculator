package de.niklaskerkhoff.processor

class Lexer(
    input: String
) {
    lateinit var currentToken: Token
    lateinit var currentValue: String

    private val input = input.map { it.toString() }.toMutableList()
    private val currentInput get() = input.first()

    private val tokens = Token.entries - Token.EOF

    init {
        lex()
    }

    fun lex() {
        if (input.isEmpty()) {
            currentToken = Token.EOF
            currentValue = ""
            return
        }

        val token = tokens.find { currentInput.matches(it.pattern) }
            ?: throw IllegalCharacterException(currentInput)

        var value: String
        var newValue = input.first()

        do {
            input.removeFirst()
            value = newValue
            newValue += input.firstOrNull()

        } while (input.isNotEmpty() && newValue.matches(token.pattern))

        currentToken = token
        currentValue = value
    }
}
