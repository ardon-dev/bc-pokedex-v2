package com.ardondev.domain.model

data class Specie(
    val id: Int = 0,
    val name: String = "",
    val textEntries: List<TextEntry> = emptyList()
)
