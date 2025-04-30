package com.ardondev.pokeapi.screens.pokemonDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ardondev.domain.model.Pokemon
import com.ardondev.pokeapi.components.AppTopBar
import com.ardondev.pokeapi.theme.CardHeadlineStyle
import com.ardondev.pokeapi.theme.NightBlue
import com.ardondev.pokeapi.theme.TitlePokemonNameStyle
import com.ardondev.pokeapi.theme.TitlePokemonNumberStyle
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
                        pokemonId = pokemon.id,
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
fun PokemonDetailTopBar(
    pokemonName: String,
    pokemonId: Int,
    onBack: () -> Unit
) {
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
        },
        trailing = {
            Text(
                "#$pokemonId",
                style = TitlePokemonNumberStyle,
                modifier = Modifier
                    .padding(end = 16.dp)
            )
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
