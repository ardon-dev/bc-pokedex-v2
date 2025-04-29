package com.ardondev.data.repository

import com.ardondev.core.network.Endpoint
import com.ardondev.data.mappers.toPokemon
import com.ardondev.data.model.PokemonListResponse
import com.ardondev.domain.model.Pokemon
import com.ardondev.domain.repository.PokemonRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonRepositoryImpl(
    private val ktorClient: HttpClient,
    private val baseUrl: String
): PokemonRepository {

    override fun fetchAllPokemon(): Flow<List<Pokemon>> = flow {
        val response = ktorClient.get(baseUrl + Endpoint.INDEX)
        val data = response.body<PokemonListResponse>()
        emit(data.results.map { it.toPokemon() })
    }

}