package com.ardondev.pokeapi.screens.pokemonDetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardondev.domain.useCase.GetSinglePokemonUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val getSinglePokemonUseCase: GetSinglePokemonUseCase
): ViewModel() {

    init {
        viewModelScope.launch {
            getSinglePokemonUseCase(25)
                .catch { e -> Log.d("PokemonDetailViewModel", e.message.orEmpty()) }
                .onEach { p -> Log.d("PokemonDetailViewModel", p.toString()) }
                .collect()
        }
    }
}