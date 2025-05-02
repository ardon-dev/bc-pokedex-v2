package com.ardondev.data.remote.api

import com.ardondev.core.network.Endpoint
import com.ardondev.core.network.routeWithParams
import com.ardondev.data.remote.model.PokemonListResponse
import com.ardondev.data.remote.model.PokemonResponse
import com.ardondev.data.remote.model.ResponseValue
import com.ardondev.data.remote.model.SpeciesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class PokemonApiService(
    private val baseUrl: String,
    private val client: HttpClient
) {

    suspend fun fetchAllPokemon(
        offset: Int,
        limit: Int
    ): List<ResponseValue> {
        val url = baseUrl + Endpoint.INDEX.route
        return client.get(url) {
            url {
                parameters.append("offset", "$offset")
                parameters.append("limit", "$limit")
            }
        }.body<PokemonListResponse>().results.orEmpty()
    }

    suspend fun fetchPokemon(id: Int): PokemonResponse {
        val url = baseUrl + Endpoint.POKEMON.routeWithParams("id" to id)
        return client.get(url) {}.body<PokemonResponse>()
    }

    suspend fun fetchSpecie(id: Int): SpeciesResponse {
        val url = baseUrl + Endpoint.SPECIES.routeWithParams("id" to id)
        return client.get(url) {}.body<SpeciesResponse>()
    }

}