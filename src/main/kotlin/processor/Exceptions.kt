package de.niklaskerkhoff.processor

class IllegalCharacterException(currentInput: String) : Exception(currentInput)

class IllegalTokenException(token: Token) : Exception(token.toString())
