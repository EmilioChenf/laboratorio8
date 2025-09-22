package com.example.lab9

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun CharacterDetailScreen(id: Int, onBack: () -> Unit) {
    val vm = remember { CharactersViewModel() }
    val c = remember(id) { vm.characters.find { it.id == id } }

    Column(Modifier.padding(16.dp)) {
        Button(onClick = onBack) { Text("← Back") }
        Spacer(Modifier.height(12.dp))
        c?.let {
            Image(
                painter = rememberAsyncImagePainter(it.image),
                contentDescription = it.name,
                modifier = Modifier.fillMaxWidth().height(240.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.height(12.dp))
            Text(it.name, style = MaterialTheme.typography.headlineSmall)
            Text("${it.species} • ${it.status}")
            Text(it.gender)
        } ?: Text("Character $id not found")
    }
}
