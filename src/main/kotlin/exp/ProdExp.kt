package de.niklaskerkhoff.exp

class ProdExp(
    private val first: Exp,
    private val second: Exp,
) : Exp {
    override fun eval() = first.eval() * second.eval()
}

