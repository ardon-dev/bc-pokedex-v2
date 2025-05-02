package com.ardondev.pokeapi.components

import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.error
import com.ardondev.domain.model.Pokemon
import com.ardondev.domain.model.Type
import com.ardondev.pokeapi.R
import com.ardondev.pokeapi.theme.lighten
import com.ardondev.pokeapi.util.getColor

@Composable
fun PokemonContainer(pokemon: Pokemon) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Background
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(150.dp)
                .background(
                    color = pokemon.types[0].getColor().lighten(0.8f),
                    shape = MaterialTheme.shapes.medium
                )
        )

        // Render
        PokemonContainerRender(
            sprite = pokemon.sprite,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 70.dp)
                .size(200.dp)
        )

        // Types
        PokemonContainerTypes(
            types = pokemon.types,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )

    }
}

@Composable
private fun PokemonContainerTypes(
    types: List<Type>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(types) { type ->
            PokemonType(
                type = type,
                color = type.getColor()
            )
        }
    }
}

@Composable
private fun PokemonContainerRender(sprite: String, modifier: Modifier = Modifier) {
    var isImageLoaded by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isImageLoaded) 1f else 0.0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        ),
        label = "zoomOut"
    )
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(sprite)
            .error(R.drawable.ic_pokeball)
            .build(),
        contentDescription = null,
        onError = { e ->
            Log.d("", e.result.throwable.message.orEmpty())
        },
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .fillMaxWidth()
            .height(200.dp),
        onSuccess = {
            isImageLoaded = true
        }
    )
}

@Preview
@Composable
fun PokemonContainerPreview() {
    PokemonContainer(Pokemon(name = "Pikachu", types = listOf(Type("Electric"), Type("Fire"))))
}