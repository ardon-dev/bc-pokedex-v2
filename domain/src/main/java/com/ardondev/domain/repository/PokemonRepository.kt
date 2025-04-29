package com.ardondev.domain.repository

import com.ardondev.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun fetchAllPokemon(): Flow<List<Pokemon>>

}