package com.example.lab9

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

// Rutas únicas a lo largo de TODA la app
object Routes {
    const val LOGIN = "login"
    const val CHARACTERS = "characters"
    const val CHARACTER_DETAIL = "characterDetail"
    const val LOCATIONS = "locations"
    const val LOCATION_DETAIL = "locationDetail"
    const val PROFILE = "profile"
}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentRoute = navController.currentBackStackEntry?.destination?.route,
                onCharacters = { navController.navigate(Routes.CHARACTERS) },
                onLocations = { navController.navigate(Routes.LOCATIONS) },
                onProfile = { navController.navigate(Routes.PROFILE) },
            )
        }
    ) { inner ->
        NavHost(
            navController = navController,
            startDestination = Routes.LOGIN,
            modifier = Modifier.padding(inner)
        ) {
            // Login
            composable(Routes.LOGIN) {
                LoginScreen(
                    onLogin = {
                        navController.navigate(Routes.CHARACTERS) {
                            popUpTo(Routes.LOGIN) { inclusive = true }
                        }
                    }
                )
            }

            // Characters
            CharactersNavGraph(
                onOpenDetail = { id -> navController.navigate("${Routes.CHARACTER_DETAIL}/$id") }
            )

            composable(
                route = "${Routes.CHARACTER_DETAIL}/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id") ?: -1
                CharacterDetailScreen(id = id, onBack = { navController.popBackStack() })
            }

            // Locations
            LocationsNavGraph(
                onOpenDetail = { id -> navController.navigate("${Routes.LOCATION_DETAIL}/$id") }
            )

            composable(
                route = "${Routes.LOCATION_DETAIL}/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id") ?: -1
                LocationDetailScreen(id = id, onBack = { navController.popBackStack() })
            }

            // Profile
            composable(Routes.PROFILE) { ProfileScreen() }
        }
    }
}
