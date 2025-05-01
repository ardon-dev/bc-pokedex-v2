package com.ardondev.pokeapi.screens.pokemonDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ardondev.domain.model.Pokemon
import com.ardondev.domain.model.Type
import com.ardondev.pokeapi.R
import com.ardondev.pokeapi.components.AppTopBar
import com.ardondev.pokeapi.components.PokemonCharacteristic
import com.ardondev.pokeapi.components.PokemonContainer
import com.ardondev.pokeapi.theme.BodyTextGray
import com.ardondev.pokeapi.theme.Divider
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
                Box(Modifier.padding(paddingValues)) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        PokemonContainer(pokemon)
                        Spacer(Modifier.height(16.dp))
                        PokemonDetailCharacteristics(
                            weight = pokemon.weight,
                            height = pokemon.height
                        )
                        Spacer(Modifier.height(16.dp))
                        PokemonDetailDescription(pokemon.description)
                    }
                }
            }
        }

        is UiState.Error -> Box { Text(uiState.message) }
    }
}

@Composable
private fun PokemonDetailCharacteristics(
    weight: Int,
    height: Int
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            PokemonCharacteristic(
                name = "Peso",
                value = "${weight / 10.0}Kg",
                iconRes = R.drawable.ic_weight,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            VerticalDivider(
                color = Divider,
                modifier = Modifier
                    .fillMaxHeight()
            )
            PokemonCharacteristic(
                name = "Altura",
                value = "${height / 10.0}m",
                iconRes = R.drawable.ic_height,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}

@Composable
fun PokemonDetailDescription(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = BodyTextGray
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun PokemonDetailTopBar(
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
            name = "Pikachu",
            types = listOf(Type("Electric")),
            height = 7,
            weight = 900,
            description = "Sample description"
        )
    )
    PokemonDetailContent(uiState, onBack = {})
}
