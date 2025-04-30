package com.ardondev.pokeapi.feature.pokemonList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ardondev.domain.model.Pokemon
import com.ardondev.pokeapi.R
import com.ardondev.pokeapi.components.AppTopBar
import com.ardondev.pokeapi.components.PokemonCard
import com.ardondev.pokeapi.util.UiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    PokemonListContent(uiState)
}

@Composable
fun PokemonListTopBar() {
    AppTopBar(
        modifier = Modifier.fillMaxWidth(),
        title = "Pokemon",
        leading = {
            Image(
                painter = painterResource(R.drawable.ic_pokeball),
                contentDescription = null
            )
        }
    )
}

@Composable
fun PokemonListContent(
    uiState: UiState<List<Pokemon>>
) {
    Scaffold(
        topBar = { PokemonListTopBar() },
        content = { padding ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                when (uiState) {
                    is UiState.Loading -> LinearProgressIndicator()
                    is UiState.Success -> PokemonList(list = uiState.data)
                    is UiState.Error -> Box { Text(uiState.message) }
                }
            }
        }
    )
}

@Composable
fun PokemonList(
    list: List<Pokemon>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        contentPadding = PaddingValues(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(
            items = list,
            key = { e -> e.name }
        ) { pokemon ->
            PokemonCard(pokemon)
        }
    }
}

@Preview
@Composable
fun PokemonListContentPreview() {
    //val uiState: UiState<List<Pokemon>> = UiState.Success(listOf(Pokemon(name = "1"), Pokemon(name = "2")))
    //val uiState: UiState<List<Pokemon>> = UiState.Success(listOf(Pokemon(name = "1"), Pokemon(name = "2")))
    val uiState: UiState<List<Pokemon>> = UiState.Loading
    PokemonListContent(uiState)
}