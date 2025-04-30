package com.ardondev.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    val count: Int? = 0,
    val next: String? = "",
    val previous: String? = "",
    val results: List<ResponseValue>? = listOf()
)

@Serializable
data class PokemonResponse(
    @SerialName("base_experience")
    val baseExperience: Int? = 0,
    val height: Int? = 0,
    val id: Int? = 0,
    val name: String? = "",
    val order: Int? = 0,
    val stats: List<PokemonResponseStat>? = listOf(),
    val types: List<PokemonResponseType>? = listOf(),
    val weight: Int? = 0
)

@Serializable
data class PokemonResponseStat(
    @SerialName("base_stat")
    val baseStat: Int? = 0,
    val effort: Int? = 0,
    val stat: ResponseValue? = ResponseValue(),
)

@Serializable
data class PokemonResponseType(
    val slot: Int? = 0,
    val type: ResponseValue? = ResponseValue()
)

@Serializable
data class SpeciesResponse(
    @SerialName("flavor_text_entries")
    val flavorTextEntries: List<SpeciesResponseTextEntry>? = listOf(),
    val id: Int? = 0,
    val name: String? = ""
)

@Serializable
data class SpeciesResponseTextEntry(
    @SerialName("flavor_text")
    val flavorText: String? = "",
    val language: ResponseValue? = ResponseValue(),
    val version: ResponseValue? = ResponseValue()
)

@Serializable
data class ResponseValue(
    val name: String? = "",
    val url: String? = ""
)