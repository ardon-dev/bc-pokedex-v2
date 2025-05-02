package com.ardondev.domain.model

data class Stat(
    val name: String = "",
    val baseStat: Int = 0,
    val effort: Int = 0
) {

    fun formattedName() = when (name) {
        "hp" -> "HP"
        "attack" -> "Ataque"
        "defense" -> "Defensa"
        "special-attack" -> "Ataque especial"
        "special-defense" -> "Defensa especial"
        "speed" -> "Velocidad"
        else -> ""
    }

}
