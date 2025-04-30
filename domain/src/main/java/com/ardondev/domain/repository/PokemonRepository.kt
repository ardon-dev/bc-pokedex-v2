package com.ardondev.domain.repository

import com.ardondev.domain.model.Pokemon
import com.ardondev.domain.model.Specie
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun fetchAllPokemon(): Flow<List<Pokemon>>

    suspend fun fetchPokemon(id: Int): Pokemon

    suspend fun fetchSpecie(id: Int): Specie

}