package com.example.lab9

data class Location(
    val id: Int,
    val name: String,
    val type: String = "Planet",
    val dimension: String = "Unknown"
)
