package com.ardondev.pokeapi.feature.pokemonList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ardondev.domain.model.Pokemon
import com.ardondev.pokeapi.R
import com.ardondev.pokeapi.components.AppSearchBar
import com.ardondev.pokeapi.components.AppTopBar
import com.ardondev.pokeapi.components.PokemonCard
import com.ardondev.pokeapi.theme.DarkBlue
import com.ardondev.pokeapi.util.UiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    PokemonListContent(
        uiState = uiState,
        searchText = viewModel.searchText
    )
}

@Composable
fun PokemonListTopBar() {
    AppTopBar(
        modifier = Modifier.fillMaxWidth(),
        title = "Pokédex",
        leading = {
            Image(
                painter = painterResource(R.drawable.ic_pokeball),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }
    )
}

@Composable
private fun PokemonListContent(
    uiState: UiState<List<Pokemon>>,
    searchText: MutableState<String>
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
                    is UiState.Success -> PokemonList(
                        list = uiState.data,
                        searchText = searchText
                    )

                    is UiState.Error -> Box { Text(uiState.message) }
                }
            }
        }
    )
}

@Composable
private fun PokemonList(
    list: List<Pokemon>,
    searchText: MutableState<String>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Welcome text
        Spacer(Modifier.height(16.dp))
        PokemonListWelcomeText(
            modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(8.dp))

        // Search bar
        AppSearchBar(
            text = searchText,
            placeHolderText = "Buscar",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        // List
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            contentPadding = PaddingValues(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(
                items = list,
                key = { e -> e.name }
            ) { pokemon ->
                PokemonCard(pokemon)
            }
        }
    }

}

@Composable
private fun PokemonListWelcomeText(
    modifier: Modifier
) {
    val styledText = buildAnnotatedString {
        append("!Hola, ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("bienvenido")
        }
        append("!")
    }
    Text(
        styledText,
        style = MaterialTheme.typography.titleMedium,
        color = DarkBlue,
        modifier = modifier
    )
}

@Preview
@Composable
fun PokemonListContentPreview() {
    val searchText = remember { mutableStateOf("") }
    val uiState: UiState<List<Pokemon>> = UiState.Success(listOf(Pokemon(name = "1"), Pokemon(name = "2")))
    //val uiState: UiState<List<Pokemon>> = UiState.Error("Error")
    //val uiState: UiState<List<Pokemon>> = UiState.Loading
    PokemonListContent(uiState, searchText)
}