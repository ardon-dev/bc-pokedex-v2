package com.ardondev.data.di

import com.ardondev.data.repository.PokemonRepositoryImpl
import com.ardondev.domain.repository.PokemonRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::PokemonRepositoryImpl) { bind<PokemonRepository>() }
}