package com.example.lab9

// Defaults para evitar errores de "faltan parámetros"
data class Character(
    val id: Int,
    val name: String,
    val status: String = "Unknown",
    val species: String = "Human",
    val gender: String = "Unknown",
    val image: String = ""
)
