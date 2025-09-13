package com.example.laboratorio8.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.laboratorio8.CharacterDb

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(navController: NavController, characterId: Int) {
    val character = CharacterDb().getCharacterById(characterId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Character Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (character == null) {
            // 🔹 Manejo de error si el personaje no existe
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Character not found", style = MaterialTheme.typography.titleMedium)
            }
        } else {
            // 🔹 Mostrar detalle si existe
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                AsyncImage(
                    model = character.image,
                    contentDescription = character.name,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(character.name, style = MaterialTheme.typography.headlineSmall)

                Spacer(modifier = Modifier.height(20.dp))

                Text("Species: ${character.species}")
                Text("Status: ${character.status}")
                Text("Gender: ${character.gender}")
            }
        }
    }
}
