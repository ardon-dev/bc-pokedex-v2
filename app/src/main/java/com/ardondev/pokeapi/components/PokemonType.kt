package com.ardondev.pokeapi.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ardondev.domain.model.Type
import com.ardondev.pokeapi.theme.Yellow
import com.ardondev.pokeapi.theme.lighten
import com.ardondev.pokeapi.util.getLabel

@Composable
fun PokemonType(type: Type, color: Color) {
    FilterChip(
        selected = false,
        onClick = {},
        label = {
            Text(
                text = type.getLabel(),
                color = color
            )
        },
        shape = CircleShape,
        colors = FilterChipDefaults.filterChipColors(
            containerColor = color.lighten(0.8f)
        ),
        border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = false,
            borderColor = color,
            selectedBorderColor = color
        )
    )
}

@Preview
@Composable
fun PokemonTypePreview() {
    PokemonType(Type("fire"), Yellow)
}