package com.ardondev.pokeapi.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

object PokemonColor {
    val Normal = Color(0xFFe6dc83)
    val Fighting = Color(0xFFC22E28)
    val Flying = Color(0xFFA98FF3)
    val Poison = Color(0xFFA33EA1)
    val Ground = Color(0xFFE2BF65)
    val Rock = Color(0xFFB6A136)
    val Bug = Color(0xFFA6B91A)
    val Ghost = Color(0xFF735797)
    val Steel = Color(0xFFB7B7CE)
    val Fire = Color(0xFFEE8130)
    val Water = Color(0xFF6390F0)
    val Grass = Color(0xFF7AC74C)
    val Electric = Color(0xFFF7D02C)
    val Psychic = Color(0xFFF95587)
    val Ice = Color(0xFF96D9D6)
    val Dragon = Color(0xFF6F35FC)
    val Dark = Color(0xFF705746)
    val Fairy = Color(0xFFD685AD)
    val Stellar = Color(0xFFA8A77A)
    val Default = Color(0xFF005FFF)
}

val Background = Color(0xFFF8F8F8)
val Accent = Color(0xFF005FFF)
val Gray = Color(0xFFAAAAAA)
val DarkGray = Color(0xFF404040)
val AlternativeGray = Color(0xFF7C7C7C)
val NightBlue = Color(0xFF01426A)
val SkyBlue = Color(0xFF2C85BC)
val DarkBlue = Color(0xFF01243A)
val Yellow = Color(0xFFFFC600)
val Divider = Color(0xFFD9D9D6)
val BodyTextGray = Color(0xFF7C7C7C)
val Dark = Color(0xFF141414)

fun Color.darken(factor: Float): Color = copy(
    red = red * (1 - factor),
    green = green * (1 - factor),
    blue = blue * (1 - factor)
)

fun Color.lighten(factor: Float): Color = copy(
    red = red + (1 - red) * factor,
    green = green + (1 - green) * factor,
    blue = blue + (1 - blue) * factor
)