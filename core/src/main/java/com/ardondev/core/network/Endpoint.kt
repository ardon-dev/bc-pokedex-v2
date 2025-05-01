package com.ardondev.core.network

enum class Endpoint(val route: String) {
    INDEX("/pokemon"),
    POKEMON("/pokemon/{id}"),
    SPECIES("/pokemon-species/{id}/")
}

fun Endpoint.routeWithParams(vararg params: Pair<String, Any>): String {
    var path = route
    params.forEach { (key, value) ->
        path = path.replace("{$key}", value.toString())
    }
    return path
}