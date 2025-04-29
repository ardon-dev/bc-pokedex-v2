package com.ardondev.pokeapi.feature.pokemonList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardondev.domain.useCase.GetAllPokemonUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val getAllPokemonUseCase: GetAllPokemonUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            getAllPokemonUseCase()
                .onEach { p ->
                    Log.d("PokemonListViewModel", p.toString())
                }
                .catch { e ->
                    Log.d("PokemonListViewModel", e.message.orEmpty())
                }
                .collect()

        }
    }
}