package com.ardondev.core.network

enum class Endpoint(val route: String) {
    INDEX("/pokemon"),
    POKEMON("/pokemon/{id}"),
    SPECIES("/pokemon-species/{id}/")
}