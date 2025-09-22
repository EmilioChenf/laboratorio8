package com.example.lab9

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

// Agrega las pantallas de Characters al grafo raíz
fun NavGraphBuilder.CharactersNavGraph(
    onOpenDetail: (Int) -> Unit
) {
    composable(Routes.CHARACTERS) {
        CharactersScreen(onOpenDetail = onOpenDetail)
    }
}
