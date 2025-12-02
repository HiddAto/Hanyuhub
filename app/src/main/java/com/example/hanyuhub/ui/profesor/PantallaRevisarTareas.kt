package com.example.hanyuhub.ui.profesor

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaRevisarTareas(
    navController: NavController) {

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
                        "Revisar Tareas: Básico - A",
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
            //Completar
            // Datos de ejemplo
            data class Tarea(val titulo: String, val descripcion: String)
            data class Alumno(val nombre: String, val tareas: List<Tarea>)

// Lista de alumnos y tareas para el curso de chino básico
            val listaAlumnos = listOf(
                Alumno(
                    "María González",
                    listOf(
                        Tarea(
                            "Tarea 1: Escribir saludos básicos en pinyin",
                            "Debes escribir 10 saludos comunes en pinyin, con su significado en español.\nEjemplo: Nǐ hǎo - Hola"
                        ),
                        Tarea(
                            "Tarea 2: Traducir frases de presentación",
                            "Traduce las frases del documento PDF 'Frases_Introduccion.pdf' y entrega tu versión."
                        ),
                        Tarea(
                            "Tarea 3: Audio de pronunciación de tonos",
                            "Graba un audio pronunciando los cuatro tonos del mandarín con ejemplos."
                        )
                    )
                ),
                Alumno(
                    "Juan Pérez",
                    listOf(
                        Tarea(
                            "Tarea 1: Escribir saludos básicos en pinyin",
                            "Debes escribir 10 saludos comunes en pinyin, con su significado en español."
                        ),
                        Tarea(
                            "Tarea 2: Redactar diálogo en chino básico",
                            "Crea un diálogo de presentación entre dos personas usando frases vistas en clase."
                        ),
                        Tarea(
                            "Tarea 4: Video pronunciando números del 1 al 10",
                            "Envía un video corto diciendo los números del 1 al 10 en mandarín."
                        )
                    )
                ),
                Alumno(
                    "Ana Torres",
                    listOf(
                        Tarea(
                            "Tarea 1: Escribir saludos básicos en pinyin",
                            "Debes escribir 10 saludos comunes en pinyin."
                        ),
                        Tarea(
                            "Tarea 3: Audio de pronunciación de tonos",
                            "Graba un audio pronunciando los cuatro tonos del mandarín."
                        ),
                        Tarea(
                            "Tarea 5: Hoja de caracteres 汉字 - escritura",
                            "Escribe 10 caracteres básicos del mandarín en tu cuaderno y súbelos escaneados."
                        )
                    )
                )
            )

// Estados para el diálogo
            var tareaSeleccionada by remember { mutableStateOf<Tarea?>(null) }
            var mostrarDialogo by remember { mutableStateOf(false) }

// Contenido principal con scroll
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(listaAlumnos) { alumno ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F7F7)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = alumno.nombre,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color(0xFF333333)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            alumno.tareas.forEach { tarea ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            tareaSeleccionada = tarea
                                            mostrarDialogo = true
                                        }
                                        .padding(vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Description,
                                        contentDescription = "Tarea",
                                        tint = Color(0xFFEE1842)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(
                                        text = tarea.titulo,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Black
                                    )
                                }
                            }
                        }
                    }
                }
            }

// Diálogo amplio para mostrar detalles de la tarea seleccionada
            if (mostrarDialogo && tareaSeleccionada != null) {
                AlertDialog(
                    onDismissRequest = { mostrarDialogo = false },
                    confirmButton = {
                        TextButton(onClick = { mostrarDialogo = false }) {
                            Text("Cerrar", color = Color(0xFFEE1842))
                        }
                    },
                    title = {
                        Text(
                            text = tareaSeleccionada!!.titulo,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xFFEE1842)
                        )
                    },
                    text = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 200.dp, max = 400.dp) // Modal amplio
                                .verticalScroll(rememberScrollState())
                        ) {
                            Text(
                                text = tareaSeleccionada!!.descripcion,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            // Aquí podrías mostrar archivos, enlaces o vista previa más adelante
                            Text(
                                text = "Contenido adjunto: Ninguno (por ahora)",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }
                    },
                    containerColor = Color.White,
                    tonalElevation = 8.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
            }
        }
    }
}
