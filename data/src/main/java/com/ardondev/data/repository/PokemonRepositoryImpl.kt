package com.ardondev.data.repository

import com.ardondev.data.mappers.toAppError
import com.ardondev.data.mappers.toPokemon
import com.ardondev.data.mappers.toSpecie
import com.ardondev.data.remote.api.PokemonApiService
import com.ardondev.domain.model.Pokemon
import com.ardondev.domain.model.Specie
import com.ardondev.domain.repository.PokemonRepository

class PokemonRepositoryImpl(
    private val pokemonApiService: PokemonApiService
) : PokemonRepository {

    override suspend fun fetchAllPokemon(
        offset: Int,
        limit: Int
    ): List<Pokemon> {
        return try {
            pokemonApiService.fetchAllPokemon(offset, limit).map { it.toPokemon() }
        } catch (e: Exception) {
            throw e.toAppError()
        }
    }

    override suspend fun fetchPokemon(id: Int): Pokemon {
        return try {
            pokemonApiService.fetchPokemon(id).toPokemon()
        } catch (e: Exception) {
            throw e.toAppError()
        }
    }

    override suspend fun fetchSpecie(id: Int): Specie {
        return try {
            pokemonApiService.fetchSpecie(id).toSpecie()
        } catch (e: Exception) {
            throw e.toAppError()
        }
    }

}