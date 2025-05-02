package com.ardondev.pokeapi

import android.app.Application
import com.ardondev.core.di.ktorModule
import com.ardondev.data.di.repositoryModule
import com.ardondev.data.di.serviceModule
import com.ardondev.domain.di.useCaseModule
import com.ardondev.pokeapi.di.viewModelModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        setupDependencyInjection()
    }

    private fun setupDependencyInjection() {
        startKoin {
            modules(
                listOf(
                    ktorModule,
                    serviceModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

}