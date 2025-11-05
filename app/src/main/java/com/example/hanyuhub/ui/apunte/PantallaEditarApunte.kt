package com.example.hanyuhub.ui.apunte

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaEditarApunteDummy(
    navController: NavController
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    // Permite controlar el foco de los elementos
    val focusManager = LocalFocusManager.current

    // Variables vacías
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var contenido by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF58078),
                    titleContentColor = Color(0xFF721313)
                ),
                title = {
                    Text(
                        "Editar Apunte",
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFF58078),
                contentColor = Color(0xFF721313)
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver"
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFC7E5FD))
                .fillMaxSize()
                .clickable(
                    // null interactionSource e indication = null
                    // para que no muestre efecto al tocar
                    indication = null,
                    interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
                ) {
                    focusManager.clearFocus() // Oculta el teclado
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFE3DF)
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Text(
                        text = "Título:",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF721313)
                    )
                    OutlinedTextField(
                        value = titulo,
                        onValueChange = { titulo = it },
                        label = { Text("Titulo") },
                        singleLine = false,
                        modifier = Modifier.fillMaxWidth(),
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Descripción:",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF721313)
                    )
                    OutlinedTextField(
                        value = descripcion,
                        onValueChange = { descripcion = it },
                        label = { Text("Descripción") },
                        singleLine = false,
                        modifier = Modifier.fillMaxWidth(),
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Fecha:",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF721313)
                    )
                    OutlinedTextField(
                        value = fecha,
                        onValueChange = { fecha = it },
                        label = { Text("Fecha") },
                        singleLine = false,
                        modifier = Modifier.fillMaxWidth(),
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Contenido:",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF721313)
                    )
                    OutlinedTextField(
                        value = contenido,
                        onValueChange = { contenido = it },
                        label = { Text("Contenido") },
                        singleLine = false,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
                Spacer(Modifier.height(12.dp))
            }
            Spacer(Modifier.height(8.dp))
            // Botón Guardar
            Button(
                onClick = {
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF58078),
                    contentColor = Color(0xFF721313)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("GUARDAR", style = MaterialTheme.typography.titleMedium)
            }

            Spacer(modifier = Modifier.height(500.dp))
        }
    }
}
