package com.ardondev.data.model

data class PokemonListResponse(
    val count: Int = 0,
    val next: String = "",
    val previous: Any = Any(),
    val results: List<PokemonListResult> = listOf()
)

data class PokemonListResult(
    val name: String = "",
    val url: String = ""
)