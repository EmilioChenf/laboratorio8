package com.example.lab9

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LocationsScreen(onOpenDetail: (Int) -> Unit) {
    val locations = remember { LocationDb.items }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(locations) { loc ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOpenDetail(loc.id) }
            ) {
                Column(Modifier.padding(12.dp)) {
                    Text(loc.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text(loc.type)
                    Text(loc.dimension)
                }
            }
        }
    }
}
