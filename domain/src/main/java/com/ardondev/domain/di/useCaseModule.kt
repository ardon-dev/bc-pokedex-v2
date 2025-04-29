package com.ardondev.domain.di

import com.ardondev.domain.useCase.GetAllPokemonUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::GetAllPokemonUseCase)
}