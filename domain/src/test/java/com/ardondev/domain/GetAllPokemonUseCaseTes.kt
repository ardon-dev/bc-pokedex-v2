package com.ardondev.domain

import com.ardondev.domain.model.Pokemon
import com.ardondev.domain.repository.PokemonRepository
import com.ardondev.domain.useCase.GetAllPokemonUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import org.mockito.kotlin.mock
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

@RunWith(JUnit4::class)
class GetAllPokemonUseCaseTest {

    private lateinit var repository: PokemonRepository
    private lateinit var getAllPokemonUseCase: GetAllPokemonUseCase

    @Before
    fun setup() {
        repository = mock()
        getAllPokemonUseCase = GetAllPokemonUseCase(repository)
    }

    @Test
    fun `invoke should emit list of pokemon from repository`() = runTest {
        val expectedPokemon = listOf(
            Pokemon(1, "Bulbasaur"),
            Pokemon(2, "Ivysaur")
        )

        `when`(repository.fetchAllPokemon(0, 2)).thenReturn(expectedPokemon)

        val result = getAllPokemonUseCase(0, 2).first()
        verify(repository).fetchAllPokemon(0, 2)

        assertEquals(expectedPokemon, result)
    }

    @Test
    fun `invoke should emit empty list when repository returns nothing`() = runTest {
        `when`(repository.fetchAllPokemon(0, 2)).thenReturn(emptyList())

        val result = getAllPokemonUseCase(0, 2).first()

        assertTrue(result.isEmpty())
    }

    @Test
    fun `invoke should call repository exactly once`() = runTest {
        `when`(repository.fetchAllPokemon(0, 2)).thenReturn(emptyList())

        getAllPokemonUseCase(0, 2).first()

        verify(repository, times(1)).fetchAllPokemon(0, 2)
    }
}
