package com.example.lab9

import androidx.compose.runtime.Composable

@Composable
fun HomeScreen() {
    // Ya no manejamos navegación aquí. Delegamos al grafo raíz.
    AppNavGraph()
}
