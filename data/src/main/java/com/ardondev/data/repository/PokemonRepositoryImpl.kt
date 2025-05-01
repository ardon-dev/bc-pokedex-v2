package com.ardondev.data.repository

import com.ardondev.core.network.Endpoint
import com.ardondev.core.network.routeWithParams
import com.ardondev.data.mappers.toPokemon
import com.ardondev.data.mappers.toSpecie
import com.ardondev.data.model.PokemonListResponse
import com.ardondev.data.model.PokemonResponse
import com.ardondev.data.model.SpeciesResponse
import com.ardondev.domain.model.Pokemon
import com.ardondev.domain.model.Specie
import com.ardondev.domain.repository.PokemonRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class PokemonRepositoryImpl(
    private val ktorClient: HttpClient,
    private val baseUrl: String
) : PokemonRepository {

    override suspend fun fetchAllPokemon(
        offset: Int,
        limit: Int
    ): List<Pokemon> {
        val url = baseUrl + Endpoint.INDEX.route
        return ktorClient.get(url) {
            url {
                parameters.append("offset", "$offset")
                parameters.append("limit", "$limit")
            }
        }.body<PokemonListResponse>().results?.map { it.toPokemon() }.orEmpty()
    }

    override suspend fun fetchPokemon(id: Int): Pokemon {
        val url = baseUrl + Endpoint.POKEMON.routeWithParams("id" to id)
        return ktorClient.get(url){}.body<PokemonResponse>().toPokemon()
    }

    override suspend fun fetchSpecie(id: Int): Specie {
        val url = baseUrl + Endpoint.SPECIES.routeWithParams("id" to id)
        return ktorClient.get(url).body<SpeciesResponse>().toSpecie()
    }

}