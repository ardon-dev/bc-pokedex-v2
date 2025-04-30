package com.ardondev.pokeapi.screens.pokemonDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.ardondev.domain.model.Pokemon
import com.ardondev.pokeapi.components.AppTopBar
import com.ardondev.pokeapi.theme.NightBlue
import com.ardondev.pokeapi.theme.TitlePokemonNameStyle
import com.ardondev.pokeapi.util.UiState
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun PokemonDetailScreen(
    id: Int,
    viewModel: PokemonDetailViewModel = koinViewModel(
        parameters = { parametersOf(id) }
    ),
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()

    PokemonDetailContent(
        uiState = uiState,
        onBack = { navController.navigateUp() }
    )
}

@Composable
fun PokemonDetailContent(
    uiState: UiState<Pokemon>,
    onBack: () -> Unit
) {
    when (uiState) {
        is UiState.Loading -> LinearProgressIndicator()
        is UiState.Success -> {
            val pokemon = uiState.data
            Scaffold(
                topBar = {
                    PokemonDetailTopBar(
                        pokemonName = pokemon.name,
                        onBack = onBack
                    )
                }
            ) { paddingValues ->

            }
        }

        is UiState.Error -> Box { Text(uiState.message) }
    }
}

@Composable
fun PokemonDetailTopBar(pokemonName: String, onBack: () -> Unit) {
    AppTopBar(
        title = pokemonName,
        titleStyle = TitlePokemonNameStyle,
        leading = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    tint = NightBlue,
                    contentDescription = null
                )
            }
        }
    )
}

@Preview
@Composable
fun PokemonDetailScreenPreview() {
    val uiState: UiState<Pokemon> = UiState.Success(
        Pokemon(
            name = "Pikachu"
        )
    )
    PokemonDetailContent(uiState, onBack = {})
}
