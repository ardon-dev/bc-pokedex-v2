package com.ardondev.pokeapi

import android.app.Application
import com.ardondev.core.di.ktorModule
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        setupDependencyInjection()
    }

    private fun setupDependencyInjection() {
        startKoin {
            modules(listOf(ktorModule))
        }
    }

}