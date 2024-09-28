package de.niklaskerkhoff.exp

class SumExp(
    private val first: Exp,
    private val second: Exp,
) : Exp {
    override fun eval() = first.eval() + second.eval()
}

