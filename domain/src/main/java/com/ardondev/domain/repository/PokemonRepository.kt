package com.ardondev.domain.repository

import com.ardondev.domain.model.Pokemon
import com.ardondev.domain.model.Specie

interface PokemonRepository {

    suspend fun fetchAllPokemon(
        offset: Int,
        limit: Int
    ): List<Pokemon>

    suspend fun fetchPokemon(id: Int): Pokemon

    suspend fun fetchSpecie(id: Int): Specie

}