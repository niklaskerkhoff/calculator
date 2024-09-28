package de.niklaskerkhoff.exp

class IntExp(
    private val value: String
) : Exp {
    override fun eval() = value.toDouble()
}
