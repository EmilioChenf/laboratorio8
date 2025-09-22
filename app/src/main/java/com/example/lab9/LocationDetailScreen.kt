package com.example.lab9

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LocationDetailScreen(id: Int, onBack: () -> Unit) {
    val loc = remember(id) { LocationDb.items.find { it.id == id } }

    Column(Modifier.padding(16.dp)) {
        Button(onClick = onBack) { Text("← Back") }
        Spacer(Modifier.height(12.dp))
        loc?.let {
            Text(it.name, style = MaterialTheme.typography.headlineSmall)
            Text(it.type)
            Text(it.dimension)
        } ?: Text("Location $id not found")
    }
}
