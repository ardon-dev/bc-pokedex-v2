package com.ardondev.pokeapi.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ardondev.pokeapi.screens.pokemonDetail.PokemonDetailScreen
import com.ardondev.pokeapi.screens.pokemonList.PokemonListScreen
import com.ardondev.pokeapi.theme.PokeapiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            PokeapiTheme {
                NavHost(
                    navController = navController,
                    startDestination = PokemonListRoute,
                ) {
                    composable<PokemonListRoute> {
                        PokemonListScreen(navController = navController)
                    }
                    composable<PokemonDetailRoute> { backStackEntry ->
                        val route: PokemonDetailRoute = backStackEntry.toRoute()
                        PokemonDetailScreen(
                            id = route.id,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}