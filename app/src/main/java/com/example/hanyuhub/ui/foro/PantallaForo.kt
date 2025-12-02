package com.example.hanyuhub.ui.foro

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import com.example.hanyuhub.model.Apuntes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaForo(
    navController: NavController,
    nombre: String,
    apellido: String,
    email: String,
    pass: String,
    curso: String
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    // Permite controlar el foco de los elementos
    val focusManager = LocalFocusManager.current

    var comentario1 by remember { mutableStateOf("") }
    var comentario2 by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF58078),
                    titleContentColor = Color(0xFF721313)
                ),
                title = {
                    Text("Foro", style = MaterialTheme.typography.headlineMedium)
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFF58078),
                contentColor = Color(0xFF721313)
            ) {
                // Botón de volver
                IconButton(onClick = {
                    navController.navigate("homeAlumno/$nombre/$apellido/$email/$pass/$curso")
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver"
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                // Botón de perfil
                IconButton(
                    onClick = {
                        navController.navigate("perfilAlumno/$nombre/$apellido/$email/$pass/$curso")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Perfil",
                        Modifier.size(36.dp)
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("crearPost") },
                containerColor = Color(0xFFFFC6C1),
                contentColor = Color(0xFF4F0606)
            ) {
                Row (modifier = Modifier.padding(5.dp)) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                    Text("Crear Post")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFC7E5FD))
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .clickable(
                    // null interactionSource e indication = null
                    // para que no muestre efecto al tocar
                    indication = null,
                    interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
                ) {
                    focusManager.clearFocus() // Oculta el teclado
                },
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(20.dp),

            ) {
            Spacer(modifier = Modifier.height(10.dp))

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFE3DF)
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(20.dp)) {
                    Text(
                        "Pregunta de la tarea 3",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF721313)
                    )

                    Spacer(Modifier.height(6.dp))

                    Text(
                        "Fecha: 10 Oct 2025",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF8A3838)
                    )

                    Text(
                        "Aliquam aliquet interdum lorem ut consequat. Cras sit amet porttitor.",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF8A3838)
                    )

                    Spacer(Modifier.height(12.dp))

                    Text(
                        "Comentarios (0)",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF8A3838)
                    )
                    Spacer(Modifier.height(25.dp))

                    OutlinedTextField(
                        value = comentario1,
                        onValueChange = { comentario1 = it },
                        label = { Text("Comentario") },
                        singleLine = false,
                        modifier = Modifier.fillMaxWidth(),
                    )

                    Spacer(Modifier.height(6.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Button(
                            onClick = { },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFFC6C1),
                                contentColor = Color(0xFF4F0606)
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Comentar")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFE3DF)
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(20.dp)) {
                    Text(
                        "Consulta sobre Vocabulario",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF721313)
                    )

                    Spacer(Modifier.height(6.dp))

                    Text(
                        "Fecha: 10 Oct 2025",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF8A3838)
                    )

                    Text(
                        "Aliquam aliquet interdum lorem ut consequat. Cras sit amet porttitor.",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF8A3838)
                    )

                    Spacer(Modifier.height(12.dp))

                    Text(
                        "Comentarios (0)",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF8A3838)
                    )
                    Spacer(Modifier.height(25.dp))

                    OutlinedTextField(
                        value = comentario2,
                        onValueChange = { comentario2 = it },
                        label = { Text("Comentario") },
                        singleLine = false,
                        modifier = Modifier.fillMaxWidth(),
                    )

                    Spacer(Modifier.height(6.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Button(
                            onClick = { },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFFC6C1),
                                contentColor = Color(0xFF4F0606)
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Comentar")
                        }
                    }
                }
            }
            Spacer(Modifier.height(60.dp))
        }
    }
}