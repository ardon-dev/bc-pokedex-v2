package com.ardondev.pokeapi.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ardondev.domain.model.Pokemon
import com.ardondev.pokeapi.theme.CardHeadlineStyle

@Composable
fun PokemonCard(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            PokemonCardNumber(pokemon.id)
            PokemonImage(pokemon.sprite)
            Text(pokemon.name)
        }
    }
}

@Composable
private fun PokemonCardNumber(number: Int) {
    Text("#${number}", style = CardHeadlineStyle)
}

@Composable
private fun PokemonImage(url: String) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = Modifier.size(100.dp)
    )
}