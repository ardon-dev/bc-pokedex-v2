package com.ardondev.domain.model

data class Pokemon(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val sprite: String = "",
    val stats: List<Stat> = emptyList(),
    val types: List<Type> = emptyList()
)
