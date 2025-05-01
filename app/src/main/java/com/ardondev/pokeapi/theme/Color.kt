package com.ardondev.pokeapi.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Accent = Color(0xFF005FFF)
val Gray = Color(0xFFAAAAAA)
val DarkGray = Color(0xFF404040)
val AlternativeGray = Color(0xFF7C7C7C)
val NightBlue = Color(0xFF01426A)
val DarkBlue = Color(0xFF01243A)
val Yellow = Color(0xFFFFC600)

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