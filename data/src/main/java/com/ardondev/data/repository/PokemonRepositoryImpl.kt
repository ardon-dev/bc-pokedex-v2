package com.ardondev.data.repository

import com.ardondev.core.network.ApiError
import com.ardondev.core.network.ApiErrorMapper
import com.ardondev.core.network.Endpoint
import com.ardondev.data.mappers.toPokemon
import com.ardondev.data.model.PokemonListResponse
import com.ardondev.domain.repository.PokemonRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PokemonRepositoryImpl(
    private val ktorClient: HttpClient,
    private val baseUrl: String
) : PokemonRepository {

    override fun fetchAllPokemon() = flow {
        val response =
            ktorClient.get(baseUrl + Endpoint.INDEX.route).body<PokemonListResponse>()
        val results = response.results
        if (results.isNullOrEmpty()) {
            throw ApiError(400, "Not found.")
        }
        emit(results.map { it.toPokemon() })
    }.catch { e ->
        throw ApiErrorMapper.map(e)
    }

}