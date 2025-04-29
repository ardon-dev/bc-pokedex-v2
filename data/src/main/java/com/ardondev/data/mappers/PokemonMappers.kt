package com.ardondev.data.mappers

import com.ardondev.data.model.PokemonListResult
import com.ardondev.domain.model.Pokemon

fun PokemonListResult.toPokemon() = Pokemon(name = this.name.orEmpty())