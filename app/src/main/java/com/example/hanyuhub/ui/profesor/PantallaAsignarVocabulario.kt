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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
fun PantallaAsignarVocabulario(
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
            Spacer(modifier = Modifier.height(20.dp))
            //Completar
            // Datos de ejemplo: colecciones de vocabulario
            data class Coleccion(val titulo: String, val descripcion: String)

            val listaColecciones = listOf(
                Coleccion("Vocabulario Texto 1", "Palabras clave del primer texto del libro Hanyu 1."),
                Coleccion("Saludos y Presentaciones", "Expresiones básicas para saludar y presentarse."),
                Coleccion("Familia", "Términos relacionados con miembros de la familia."),
                Coleccion("Animales", "Nombres comunes de animales."),
                Coleccion("Colores", "Vocabulario para describir colores y objetos."),
                Coleccion("Comida y Bebidas", "Palabras relacionadas con comidas y bebidas básicas.")
            )

// Estados para selección y diálogo
            var seleccionadas by remember { mutableStateOf(setOf<String>()) }
            var mostrarDialogo by remember { mutableStateOf(false) }

// Contenido principal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {

                Text(
                    text = "Colecciones de vocabulario disponibles",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF333333)
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(listaColecciones) { coleccion ->
                        val isSelected = seleccionadas.contains(coleccion.titulo)

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .clickable {
                                    seleccionadas =
                                        if (isSelected) seleccionadas - coleccion.titulo
                                        else seleccionadas + coleccion.titulo
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = if (isSelected) Color(0xFFFFE5EB) else Color(0xFFF8F8F8)
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = isSelected,
                                    onCheckedChange = {
                                        seleccionadas =
                                            if (it) seleccionadas + coleccion.titulo
                                            else seleccionadas - coleccion.titulo
                                    },
                                    colors = CheckboxDefaults.colors(checkedColor = Color(0xFFEE1842))
                                )

                                Spacer(modifier = Modifier.width(12.dp))

                                Column {
                                    Text(
                                        text = coleccion.titulo,
                                        style = MaterialTheme.typography.titleSmall,
                                        color = Color.Black
                                    )
                                    Text(
                                        text = coleccion.descripcion,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.DarkGray
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(0.dp))

                // Botón para asignar vocabulario
                Button(
                    onClick = {
                        if (seleccionadas.isNotEmpty()) {
                            mostrarDialogo = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEE1842))
                ) {
                    Text(
                        text = "Asignar Vocabulario",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

// Modal de confirmación
            if (mostrarDialogo) {
                AlertDialog(
                    onDismissRequest = { mostrarDialogo = false },
                    confirmButton = {
                        TextButton(onClick = { mostrarDialogo = false }) {
                            Text("Aceptar", color = Color(0xFFEE1842))
                        }
                    },
                    title = {
                        Text(
                            text = "Vocabulario asignado con éxito",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xFFEE1842)
                        )
                    },
                    text = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 150.dp, max = 300.dp)
                                .verticalScroll(rememberScrollState()),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "Las siguientes colecciones fueron asignadas al curso:",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            seleccionadas.forEach {
                                Text("• $it", style = MaterialTheme.typography.bodyMedium)
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Los alumnos podrán acceder a este nuevo vocabulario en la próxima sesión.",
                                style = MaterialTheme.typography.bodySmall,
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