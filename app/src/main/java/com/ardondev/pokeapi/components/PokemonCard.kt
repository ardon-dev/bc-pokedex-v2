package com.ardondev.pokeapi.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.error
import com.ardondev.domain.model.Pokemon
import com.ardondev.pokeapi.R
import com.ardondev.pokeapi.theme.CardHeadlineStyle
import com.ardondev.pokeapi.theme.CardTitleStyle

@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            PokemonCardNumber(pokemon.id)
            PokemonCardImage(pokemon.sprite)
            Spacer(Modifier.height(16.dp))
            PokemonCardName(pokemon.name)
        }
    }
}

@Composable
private fun PokemonCardName(name: String) {
    Text(
        text = name,
        style = CardTitleStyle,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun PokemonCardNumber(number: Int) {
    Text(
        text = "#${number}",
        style = CardHeadlineStyle,
        textAlign = TextAlign.End,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
private fun PokemonCardImage(url: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .error(R.drawable.ic_pokeball)
            .build(),
        contentDescription = null,
        onError = { e ->
            Log.d("", e.result.throwable.message.orEmpty())
        },
        modifier = Modifier.size(100.dp)
    )
}

@Preview
@Composable
fun PokemonCardPreview() {
    PokemonCard(
        onClick = {},
        pokemon = Pokemon(
            id = 25,
            name = "Pikachu",
            sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/25.png"
        )
    )
}