package de.niklaskerkhoff.exp

class DoubleExp(
    private val intPart: String,
    private val doublePart: String
) : Exp {
    override fun eval() = "$intPart.$doublePart".toDouble()
}

