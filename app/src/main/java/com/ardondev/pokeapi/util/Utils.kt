package com.ardondev.pokeapi.util

import android.content.Context
import android.os.Build
import androidx.compose.ui.graphics.Color
import coil3.ImageLoader
import coil3.gif.AnimatedImageDecoder
import coil3.gif.GifDecoder
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

fun Type.getLabel(): String {
    return when (this.name) {
        "normal" -> "\uD83D\uDD18 Normal"
        "fighting" -> "\uD83E\uDD4A Lucha"
        "flying" -> "\uD83E\uDEBD Volador"
        "poison" -> "☠\uFE0F Veneno"
        "ground" -> "⛰\uFE0F Tierra"
        "rock" -> "\uD83E\uDEA8 Roca"
        "bug" -> "\uD83E\uDEB2 Bicho"
        "ghost" -> "\uD83D\uDC7B Fantasma"
        "steel" -> "⚙\uFE0F Acero"
        "fire" -> "\uD83D\uDD25 Fuego"
        "water" -> "\uD83C\uDF0A Agua"
        "grass" -> "\uD83C\uDF3F Planta"
        "electric" -> "⚡ Eléctrico"
        "psychic" -> "\uD83D\uDC41\uFE0F Psíquico"
        "ice" -> "❄\uFE0F Hielo"
        "dragon" -> "\uD83D\uDC32 Dragón"
        "dark" -> "\uD83C\uDF11 Siniestro"
        "fairy" -> "✨ Ada"
        "stellar" -> "Estelar"
        else -> this.name
    }
}

fun getGifImageLoader(context: Context): ImageLoader {
    return ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(AnimatedImageDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
}
