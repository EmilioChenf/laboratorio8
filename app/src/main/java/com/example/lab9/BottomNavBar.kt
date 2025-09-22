package com.example.lab9

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomNavBar(
    currentRoute: String?,
    onCharacters: () -> Unit,
    onLocations: () -> Unit,
    onProfile: () -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == Routes.CHARACTERS,
            onClick = onCharacters,
            icon = { Icon(Icons.Default.Person, contentDescription = "Characters") },
            label = { Text("Characters") }
        )
        NavigationBarItem(
            selected = currentRoute == Routes.LOCATIONS,
            onClick = onLocations,
            icon = { Icon(Icons.Default.LocationOn, contentDescription = "Locations") },
            label = { Text("Locations") }
        )
        NavigationBarItem(
            selected = currentRoute == Routes.PROFILE,
            onClick = onProfile,
            icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Profile") },
            label = { Text("Profile") }
        )
    }
}
