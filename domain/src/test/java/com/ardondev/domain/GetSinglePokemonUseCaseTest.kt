package com.ardondev.domain

import com.ardondev.domain.model.AppError
import com.ardondev.domain.model.Pokemon
import com.ardondev.domain.model.Specie
import com.ardondev.domain.model.TextEntry
import com.ardondev.domain.repository.PokemonRepository
import com.ardondev.domain.useCase.GetSinglePokemonUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import org.mockito.kotlin.mock
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class GetSinglePokemonUseCaseTest {

    private lateinit var repository: PokemonRepository
    private lateinit var getSinglePokemonUseCase: GetSinglePokemonUseCase
    private val id = 1
    private val name = "Bulbasaur"
    private val esEntry = "Descripción"

    @Before
    fun setup() {
        repository = mock()
        getSinglePokemonUseCase = GetSinglePokemonUseCase(repository)
    }

    @Test
    fun `invoke should return pokemon with spanish description`() = runTest {
        // Given
        val basePokemon = Pokemon(id, name)
        val specie = Specie(
            id = id,
            name = name,
            textEntries = listOf(
                TextEntry(
                    text = esEntry,
                    language = "es"
                ),
                TextEntry(
                    text = "Description",
                    language = "en"
                )
            )
        )

        // When
        `when`(repository.fetchPokemon(id)).thenReturn(basePokemon)
        `when`(repository.fetchSpecie(id)).thenReturn(specie)
        val result = getSinglePokemonUseCase(id).first()

        // Them
        val expected = basePokemon.copy(description = esEntry)
        assertEquals(expected, result)
    }

    @Test
    fun `invoke should return pokemon with empty description if spanish not found`() = runTest {
        // Given
        val basePokemon = Pokemon(id, name)
        val specie = Specie(
            id = id,
            name = name,
            textEntries = listOf(
                TextEntry(
                    text = "Description",
                    language = "en"
                )
            )
        )

        // When
        `when`(repository.fetchPokemon(id)).thenReturn(basePokemon)
        `when`(repository.fetchSpecie(id)).thenReturn(specie)
        val result = getSinglePokemonUseCase(id).first()

        // Then
        assertEquals("", result.description)
    }

    @Test(expected = Throwable::class)
    fun `invoke should throw exception if fetchPokemon fails`() = runTest {
        // When
        `when`(repository.fetchPokemon(id)).thenThrow(AppError(404, "404"))
        getSinglePokemonUseCase(id).first()
    }

}