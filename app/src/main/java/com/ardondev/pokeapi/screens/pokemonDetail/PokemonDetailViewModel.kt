package com.ardondev.pokeapi.screens.pokemonDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardondev.domain.model.Pokemon
import com.ardondev.domain.useCase.GetSinglePokemonUseCase
import com.ardondev.pokeapi.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val getSinglePokemonUseCase: GetSinglePokemonUseCase,
    private val id: Int
): ViewModel() {

    /* Pokemon */

    private val _uiState = MutableStateFlow<UiState<Pokemon>>(UiState.Loading)
    val uiState: StateFlow<UiState<Pokemon>> = _uiState.asStateFlow()

    fun getSinglePokemon() {
        viewModelScope.launch {
            getSinglePokemonUseCase(id)
                .onEach { pokemon -> _uiState.value = UiState.Success(pokemon) }
                .catch { e -> _uiState.value = UiState.Error(e.message.orEmpty()) }
                .collect()
        }
    }

    init {
        getSinglePokemon()
    }

}