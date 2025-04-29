package com.ardondev.pokeapi.di

import com.ardondev.pokeapi.feature.pokemonList.PokemonListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::PokemonListViewModel)
}