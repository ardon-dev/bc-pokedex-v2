package com.ardondev.pokeapi.screens.pokemonList

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
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.navigation.NavController
import com.ardondev.domain.model.Pokemon
import com.ardondev.pokeapi.R
import com.ardondev.pokeapi.components.AppSearchBar
import com.ardondev.pokeapi.components.AppTopBar
import com.ardondev.pokeapi.components.PokemonCard
import com.ardondev.pokeapi.components.StatusError
import com.ardondev.pokeapi.components.StatusLoading
import com.ardondev.pokeapi.screens.PokemonDetailRoute
import com.ardondev.pokeapi.theme.DarkBlue
import com.ardondev.pokeapi.theme.TitleStyle
import com.ardondev.pokeapi.util.UiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = koinViewModel(),
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()
    PokemonListContent(
        uiState = uiState,
        searchText = viewModel.searchText,
        onPokemonClick = { id ->
            navController.navigate(
                PokemonDetailRoute(id)
            )
        },
        onRetry = {
            viewModel.setUiState(UiState.Loading)
            viewModel.getAllPokemon()
        }
    )
}

@Composable
fun PokemonListTopBar() {
    AppTopBar(
        modifier = Modifier.fillMaxWidth(),
        title = "Pokédex",
        titleStyle = TitleStyle,
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
    searchText: MutableState<String>,
    onPokemonClick: (id: Int) -> Unit,
    onRetry: () -> Unit
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
                    is UiState.Loading -> StatusLoading()
                    is UiState.Success -> PokemonList(
                        list = uiState.data,
                        searchText = searchText,
                        onPokemonClick = { onPokemonClick(it) }
                    )

                    is UiState.Error -> StatusError(
                        error = uiState.error,
                        retryButtonText = "Reintentar",
                        onRetry = onRetry
                    )
                }
            }
        }
    )
}

@Composable
private fun PokemonList(
    list: List<Pokemon>,
    searchText: MutableState<String>,
    onPokemonClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Welcome text
        Spacer(Modifier.height(8.dp))
        PokemonListWelcomeText(
            modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(16.dp))

        // Search bar
        AppSearchBar(
            text = searchText,
            placeHolderText = "Buscar",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        // List
        val gridState = rememberLazyGridState()
        LaunchedEffect(key1 = searchText.value) {
            val index = list.indexOfFirst {
                it.name.contains(searchText.value, ignoreCase = true)
            }
            if (index >= 0) {
                gridState.animateScrollToItem(index)
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2),
            contentPadding = PaddingValues(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            state = gridState
        ) {
            items(
                items = list.filter {
                    it.name.contains(searchText.value) || it.id.toString()
                        .contains(searchText.value)
                },
                key = { e -> e.id }
            ) { pokemon ->
                PokemonCard(
                    pokemon = pokemon,
                    onClick = { onPokemonClick(pokemon.id) }
                )
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
    val uiState: UiState<List<Pokemon>> =
        UiState.Success(listOf(Pokemon(name = "1"), Pokemon(name = "2")))
    //val uiState: UiState<List<Pokemon>> = UiState.Error("Error")
    //val uiState: UiState<List<Pokemon>> = UiState.Loading
    PokemonListContent(uiState, searchText, onPokemonClick = {}, onRetry = {})
}