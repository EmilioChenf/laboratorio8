package com.example.lab9

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

// Agrega las pantallas de Locations al grafo raíz
fun NavGraphBuilder.LocationsNavGraph(
    onOpenDetail: (Int) -> Unit
) {
    composable(Routes.LOCATIONS) {
        LocationsScreen(onOpenDetail = onOpenDetail)
    }
}
