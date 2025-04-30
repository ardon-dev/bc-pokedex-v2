package com.ardondev.pokeapi.screens.pokemonDetail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonDetailScreen(
    id: Int,
    viewModel: PokemonDetailViewModel = koinViewModel()
) {
    Text("detail")
}