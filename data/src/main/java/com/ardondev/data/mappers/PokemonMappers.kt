package com.ardondev.data.mappers

import com.ardondev.data.model.PokemonListResult
import com.ardondev.domain.model.Pokemon

fun PokemonListResult.toDomain(): Pokemon {
    val id = this.url?.substringBeforeLast("/")?.substringAfterLast("/")?.toIntOrNull() ?: 0
    val sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$id.png"
    return Pokemon(
        id = id,
        name = this.name.orEmpty(),
        sprite = sprite
    )
}