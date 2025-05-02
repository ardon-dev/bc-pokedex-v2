package com.ardondev.domain.useCase

import com.ardondev.domain.model.Pokemon
import com.ardondev.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetSinglePokemonUseCase(
    private val pokemonRepository: PokemonRepository
) {

    operator fun invoke(id: Int): Flow<Pokemon> = flow {
        var pokemon = pokemonRepository.fetchPokemon(id)
        val specie = pokemonRepository.fetchSpecie(id)
        specie.textEntries.firstOrNull { it.language == "es" }?.let {
            pokemon = pokemon.copy(description = it.text)
        }
        emit(pokemon)
    }.catch { throw it }

}