package de.niklaskerkhoff.utils

import kotlin.math.pow
import kotlin.math.round

fun Double.roundTo(decimals: Int): Double {
    if (decimals >= 16) return this

    val factor = 10.0.pow(decimals)

    return round(this * factor) / factor
}
