package com.example.hanyuhub.ui.tarea

import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.hanyuhub.model.Tareas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaTareas(
    navController: NavController,
    nombre: String,
    apellido: String,
    email: String,
    pass: String,
    curso: String
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    // Data Dummy
    val tareas = remember { mutableStateListOf(
        Tareas(
            "Practicar saludos básicos",
            "Graba un audio saludando en chino.",
            "05 Oct 2025",
            "08 Oct 2025",
            "Entrega el audio en formato mp3."
        ),
        Tareas(
            "Ejercicio de pinyin y tonos",
            "Completa la hoja de ejercicios sobre los tonos.",
            "08 Oct 2025",
            "11 Oct 2025",
            "Sube una foto de tu hoja completada."
        )
    )}

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF58078),
                    titleContentColor = Color(0xFF721313)
                ),
                title = {
                    Text("MIS TAREAS", style = MaterialTheme.typography.headlineMedium)
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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFC7E5FD))
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(20.dp),

            ) {
            Spacer(modifier = Modifier.height(10.dp))

            tareas.forEach { tarea ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFE3DF)
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(20.dp)) {
                        Text(
                            tarea.titulo,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xFF4F0606)
                        )

                        Spacer(Modifier.height(4.dp))

                        Text(
                            tarea.descripcion,
                            color = Color(0xFF4F0606)
                        )

                        Spacer(Modifier.height(12.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)){
                            Text(
                                "Publicada: ${tarea.fechaPub}",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF8A3838)
                            )

                            Text(
                                "Fecha Limite: ${tarea.fechaLim}",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF8A3838)
                            )
                        }

                        Spacer(Modifier.height(12.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Button(
                                onClick = {
                                    navController.navigate("verTarea")
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFFFC6C1),
                                    contentColor = Color(0xFF4F0606)
                                ),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Revisar tarea")
                            }

                            Button(
                                onClick = {  },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFF58078),
                                    contentColor = Color(0xFF721313)
                                ),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Completar tarea")
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}