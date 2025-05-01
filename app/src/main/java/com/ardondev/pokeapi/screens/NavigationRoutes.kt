package com.ardondev.pokeapi.screens

import kotlinx.serialization.Serializable

@Serializable
object PokemonListRoute

@Serializable
data class PokemonDetailRoute(val id: Int)