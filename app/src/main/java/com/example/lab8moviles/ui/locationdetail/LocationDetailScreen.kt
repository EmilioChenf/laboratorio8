package com.example.lab8moviles.ui.locationdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab8moviles.data.Location
import com.example.lab8moviles.ui.components.ErrorScreen
import com.example.lab8moviles.ui.components.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDetailScreen(
    locationId: Int,
    onBack: () -> Unit,
    viewModel: LocationDetailViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Detalle del Lugar",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(android.R.drawable.ic_menu_revert),
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1976D2)
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(Color(0xFFBBDEFB), Color(0xFFE3F2FD))
                    )
                )
                .padding(padding)
        ) {
            when {
                uiState.isLoading -> LoadingScreen()
                uiState.hasError -> ErrorScreen(
                    errorMessage = "Error al obtener información del lugar.\nIntenta de nuevo.",
                    onRetry = { viewModel.loadLocation() }
                )
                uiState.data != null -> {
                    LocationDetailContent(
                        location = uiState.data!!,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun LocationDetailContent(
    location: Location,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(Modifier.height(20.dp))

        // Ícono principal (según tipo)
        LocationIcon(type = location.type)

        Spacer(Modifier.height(24.dp))

        Text(
            text = location.name,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            color = Color(0xFF0D47A1),
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(32.dp))

        // Card con información general
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                InfoRow(label = "ID:", value = location.id.toString())
                InfoRow(label = "Tipo:", value = location.type)
                InfoRow(label = "Dimensión:", value = location.dimension)
            }
        }
    }
}

@Composable
fun LocationIcon(type: String) {
    // Colores personalizados según el tipo
    val backgroundColor = when (type.lowercase()) {
        "planet" -> Color(0xFF2196F3)
        "space station" -> Color(0xFF9C27B0)
        "microverse" -> Color(0xFF009688)
        "tv" -> Color(0xFFFF7043)
        "resort" -> Color(0xFFFFCA28)
        "fantasy town" -> Color(0xFF5C6BC0)
        "dream" -> Color(0xFF4DB6AC)
        "cluster" -> Color(0xFF8BC34A)
        else -> Color(0xFF90A4AE)
    }

    val textColor = Color.White

    Box(
        modifier = Modifier
            .size(130.dp)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = type.take(3).uppercase(),
            color = textColor,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1565C0)
        )
        Text(
            text = value,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.DarkGray
        )
    }
}
