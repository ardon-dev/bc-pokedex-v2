package com.ardondev.pokeapi.util

import androidx.compose.ui.graphics.Color
import com.ardondev.domain.model.Type
import com.ardondev.pokeapi.theme.Gray
import com.ardondev.pokeapi.theme.Yellow

fun Type.getColor(): Color {
    val color = when (this.name) {
        "" -> Yellow
        else -> Gray
    }
    return color
}