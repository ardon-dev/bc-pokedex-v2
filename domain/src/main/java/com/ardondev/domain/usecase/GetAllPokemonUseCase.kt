package com.ardondev.domain.usecase

import com.ardondev.domain.model.Pokemon
import com.ardondev.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetAllPokemonUseCase(
    private val pokemonRepository: PokemonRepository
) {

    operator fun invoke(): Flow<List<Pokemon>> = pokemonRepository.fetchAllPokemon()

}