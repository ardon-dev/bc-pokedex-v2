package com.ardondev.data.di

import com.ardondev.data.remote.api.PokemonApiService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val serviceModule = module {
    singleOf(::PokemonApiService)
}