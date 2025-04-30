package com.ardondev.domain.di

import com.ardondev.domain.useCase.GetAllPokemonUseCase
import com.ardondev.domain.useCase.GetSinglePokemonUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::GetAllPokemonUseCase)
    factoryOf(::GetSinglePokemonUseCase)
}