package com.example.laboratorio8.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.laboratorio8.CharacterDb
import com.example.laboratorio8.Character
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(navController: NavController) {
    val characters: List<Character> = CharacterDb().getAllCharacters()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Characters") }) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(characters) { character ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("characterDetail/${character.id}") }
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    AsyncImage(
                        model = character.image,
                        contentDescription = character.name,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(character.name, style = MaterialTheme.typography.titleMedium)
                        Text("${character.species} - ${character.status}")
                    }
                }
                Divider()
            }
        }
    }
}
