package com.example.laboratorio8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.laboratorio8.ui.screens.CharacterDetailScreen
import com.example.laboratorio8.ui.screens.CharacterListScreen
import com.example.laboratorio8.ui.screens.LoginScreen
import com.example.laboratorio8.ui.theme.Laboratorio8Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio8Theme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(onLoginClick = {
                // 👇 Navega directo sin usar popUpTo (para evitar el crash)
                navController.navigate("characters")
            })
        }
        composable("characters") {
            CharacterListScreen(navController = navController)
        }
        composable("characterDetail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            id?.let {
                CharacterDetailScreen(navController = navController, characterId = it)
            }
        }
    }
}
