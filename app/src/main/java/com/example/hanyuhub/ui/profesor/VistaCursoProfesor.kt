package com.example.hanyuhub.ui.profesor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VistaCursoProfesor(
    navController: NavController) {

    // Estado del diálogo (visible u oculto)
    var mostrarDialogo by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.White,
        topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFFEE1842),
                        titleContentColor = Color(0xFFFFFFFF)
                    ),
                title = {
                    Text(
                        "Curso: Básico - A",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            )
        },
         bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFEE1842),
                contentColor = Color(0xFFFFFFFF)
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
                .background(Color.White)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Título del curso
            Spacer(modifier = Modifier.height(20.dp))

            // Botón: Ver miembros del curso
            OutlinedButton(
                onClick = { mostrarDialogo = true },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(90.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFF67C587),
                    contentColor = Color.White,
                )
            ) {
                Text("Ver miembros del curso", fontSize = 20.sp)
            }

            // Botón: Asignar tarea
            OutlinedButton(
                onClick = { navController.navigate("asignarTarea") },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(90.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFFF38B84),
                    contentColor = Color.White,
                )
            ) {
                Text("Asignar Tarea", fontSize = 20.sp)
            }

            // Botón: Revisar tareas
            OutlinedButton(
                onClick = { navController.navigate("revisarTareas") },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(90.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFFFFC107),
                    contentColor = Color.White,
                )
            ) {
                Text("Revisar Tareas", fontSize = 20.sp)
            }

            // Botón: Asignar vocabulario
            OutlinedButton(
                onClick = { navController.navigate("asignarVocabulario")  },
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
                    .height(90.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFF4C99EF),
                    contentColor = Color.White,
                )
            ) {
                Text("Asignar Vocabulario", fontSize = 20.sp)
            }

        }
    }

    // Ventana modal de miembros del curso
    if (mostrarDialogo) {
        AlertDialog(
            onDismissRequest = { mostrarDialogo = false },
            confirmButton = {
                TextButton(onClick = { mostrarDialogo = false }) {
                    Text("Cerrar", fontSize = 16.sp)
                }
            },
            title = {
                Text(
                    "Miembros del Curso",
                    style = MaterialTheme.typography.titleLarge.copy(color = Color(0xFF333333))
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF7F7F7), RoundedCornerShape(10.dp))
                        .padding(0.dp)
                ) {
                    // Encabezado
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF4C99EF))
                            .padding(vertical = 10.dp, horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Estudiante", color = Color.White, fontWeight = FontWeight.Bold)
                        Text("Pendientes", color = Color.White, fontWeight = FontWeight.Bold)
                        Text("Completadas", color = Color.White, fontWeight = FontWeight.Bold)
                    }

                    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

                    // Fila 1
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFFFFFFF))
                            .padding(vertical = 10.dp, horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Iván Carrasco", color = Color(0xFF333333))
                        Text("2", color = Color(0xFFE57373))
                        Text("5", color = Color(0xFF4CAF50))
                    }

                    HorizontalDivider(thickness = 1.dp, color = Color(0xFFE0E0E0))

                    // Fila 2
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF2F6FC))
                            .padding(vertical = 10.dp, horizontal = 2.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Antonio Chihuailaf", color = Color(0xFF333333))
                        Text("1", color = Color(0xFFE57373))
                        Text("6", color = Color(0xFF4CAF50))
                    }
                }
            },
            shape = RoundedCornerShape(20.dp),
            containerColor = Color.White
        )
    }
}

