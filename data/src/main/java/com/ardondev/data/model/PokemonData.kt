package com.ardondev.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    val count: Int? = 0,
    val next: String? = "",
    val previous: String? = "",
    val results: List<PokemonListResult>? = listOf()
)

@Serializable
data class PokemonListResult(
    val name: String? = "",
    val url: String? = ""
)