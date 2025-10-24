package com.example.lab8moviles.ui.login

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.lab8moviles.data.datastore.UserPreferencesManager
import com.example.lab8moviles.data.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onStart: () -> Unit, @DrawableRes logoRes: Int? = null, logoUrl: String? = null) {
    val context = LocalContext.current
    val userPrefs = remember { UserPreferencesManager(context) }
    val authRepo = remember { AuthRepository(userPrefs) }

    var userName by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(Color(0xFF1976D2), Color(0xFF64B5F6))
                    )
                )
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(28.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(30.dp))

                // --- Encabezado / Logo ---
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(top = 40.dp)
                ) {
                    if (logoRes != null) {
                        Image(
                            painter = painterResource(id = logoRes),
                            contentDescription = "Logo",
                            modifier = Modifier
                                .size(180.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.2f))
                                .padding(16.dp)
                        )
                    } else if (!logoUrl.isNullOrEmpty()) {
                        AsyncImage(
                            model = logoUrl,
                            contentDescription = "Logo",
                            modifier = Modifier
                                .size(180.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.2f))
                                .padding(16.dp)
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .size(180.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.3f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Sin logo", color = Color.White)
                        }
                    }

                    Spacer(Modifier.height(20.dp))
                    Text(
                        text = "Bienvenido",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Inicia sesión para continuar",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 15.sp
                    )
                }

                // --- Campo de texto y botón ---
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .padding(24.dp)
                        .fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = userName,
                        onValueChange = { userName = it },
                        label = { Text("Nombre de usuario") },
                        placeholder = { Text("Ingresa tu nombre") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading
                    )

                    Spacer(Modifier.height(24.dp))

                    if (isLoading) {
                        CircularProgressIndicator()
                        Spacer(Modifier.height(12.dp))
                        Text("Iniciando sesión...", color = Color.Gray)
                    } else {
                        Button(
                            onClick = {
                                if (userName.isNotBlank()) {
                                    scope.launch {
                                        isLoading = true
                                        authRepo.login(userName.trim())
                                        delay(1000)
                                        isLoading = false
                                        onStart()
                                    }
                                }
                            },
                            enabled = userName.isNotBlank(),
                            shape = RoundedCornerShape(40),
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth()
                        ) {
                            Text("Entrar", fontSize = 18.sp)
                        }
                    }
                }

                // --- Pie de página ---
                Text(
                    text = "Desarrollado por Emilio Chen - 24841",
                    color = Color.White.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
        }
    }
}
