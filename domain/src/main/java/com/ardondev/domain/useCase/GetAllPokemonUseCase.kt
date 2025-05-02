package com.ardondev.domain.useCase

import com.ardondev.domain.model.Pokemon
import com.ardondev.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetAllPokemonUseCase(
    private val pokemonRepository: PokemonRepository
) {

    operator fun invoke(
        offset: Int,
        limit: Int,
    ): Flow<List<Pokemon>> = flow {
        val pokemonList = pokemonRepository.fetchAllPokemon(offset, limit)
        emit(pokemonList)
    }.catch { throw it }

}