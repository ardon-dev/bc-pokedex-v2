package com.ardondev.data.mappers

import com.ardondev.data.model.PokemonResponse
import com.ardondev.data.model.PokemonResponseStat
import com.ardondev.data.model.PokemonResponseType
import com.ardondev.data.model.ResponseValue
import com.ardondev.data.model.SpeciesResponse
import com.ardondev.data.model.SpeciesResponseTextEntry
import com.ardondev.domain.model.Pokemon
import com.ardondev.domain.model.Specie
import com.ardondev.domain.model.Stat
import com.ardondev.domain.model.TextEntry
import com.ardondev.domain.model.Type

fun ResponseValue.toPokemon(): Pokemon {
    val id = this.url?.substringBeforeLast("/")?.substringAfterLast("/")?.toIntOrNull() ?: 0
    val sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$id.png"
    return Pokemon(
        id = id,
        name = this.name.orEmpty(),
        sprite = sprite
    )
}

fun PokemonResponse.toPokemon(): Pokemon {
    return Pokemon(
        id = this.id ?: 0,
        name = this.name.orEmpty(),
        height = this.height ?: 0,
        weight = this.weight ?: 0,
        sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$id.png",
        stats = this.stats?.map { it.toStat() }.orEmpty(),
        types = this.types?.map { it.toType() }.orEmpty()
    )
}

fun PokemonResponseStat.toStat(): Stat {
    return Stat(
        name = this.stat?.name.orEmpty(),
        baseStat = this.baseStat ?: 0,
        effort = this.effort ?: 0
    )
}

fun PokemonResponseType.toType(): Type {
    return Type(
        name = this.type?.name.orEmpty()
    )
}

fun SpeciesResponse.toSpecie(): Specie {
    return Specie(
        id = this.id ?: 0,
        textEntries = this.flavorTextEntries?.map { it.toTextEntry() }.orEmpty(),
        name = this.name.orEmpty()
    )
}

fun SpeciesResponseTextEntry.toTextEntry(): TextEntry {
    return TextEntry(
        text = this.flavorText.orEmpty(),
        language = this.language?.name.orEmpty(),
        version = this.version?.name.orEmpty()
    )
}