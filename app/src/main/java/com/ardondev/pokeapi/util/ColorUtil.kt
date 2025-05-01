package com.ardondev.pokeapi.util

import androidx.compose.ui.graphics.Color
import com.ardondev.domain.model.Type
import com.ardondev.pokeapi.theme.PokemonColor

fun Type.getColor(): Color {
    val color = when (this.name) {
        "normal" -> PokemonColor.Normal
        "fighting" -> PokemonColor.Fighting
        "flying" -> PokemonColor.Flying
        "poison" -> PokemonColor.Poison
        "ground" -> PokemonColor.Ground
        "rock" -> PokemonColor.Rock
        "bug" -> PokemonColor.Bug
        "ghost" -> PokemonColor.Ghost
        "steel" -> PokemonColor.Steel
        "fire" -> PokemonColor.Fire
        "water" -> PokemonColor.Water
        "grass" -> PokemonColor.Grass
        "electric" -> PokemonColor.Electric
        "psychic" -> PokemonColor.Psychic
        "ice" -> PokemonColor.Ice
        "dragon" -> PokemonColor.Dragon
        "dark" -> PokemonColor.Dark
        "fairy" -> PokemonColor.Fairy
        "stellar" -> PokemonColor.Stellar
        else -> PokemonColor.Default
    }
    return color
}