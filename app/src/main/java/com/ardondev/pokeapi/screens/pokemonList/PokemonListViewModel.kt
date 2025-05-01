package com.ardondev.pokeapi.screens.pokemonList

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardondev.domain.model.Pokemon
import com.ardondev.domain.useCase.GetAllPokemonUseCase
import com.ardondev.pokeapi.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val getAllPokemonUseCase: GetAllPokemonUseCase
) : ViewModel() {

    /* Search */

    val searchText = mutableStateOf("")

    /* Pokemon list */

    private val _uiState = MutableStateFlow<UiState<List<Pokemon>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Pokemon>>> = _uiState.asStateFlow()

    fun getAllPokemon() {
        viewModelScope.launch {
            getAllPokemonUseCase(
                offset = 0,
                limit = 1302
            )
                .onEach { list -> _uiState.value = UiState.Success(list) }
                .catch { e -> _uiState.value = UiState.Error(e.message.orEmpty()) }
                .collect()
        }
    }

    init {
        getAllPokemon()
    }

}