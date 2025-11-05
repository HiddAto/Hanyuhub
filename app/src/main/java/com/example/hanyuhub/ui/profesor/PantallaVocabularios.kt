package com.example.hanyuhub.ui.profesor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hanyuhub.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaVocabularios(
    navController: NavController
) {
    val imagenes = listOf(R.drawable.laoshi_1)

    var imagenActual by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            imagenActual = if (imagenActual + 1 < imagenes.size) imagenActual + 1 else 0
        }
    }

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
                        "Mis Cursos",
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
        ) {
            //Completar
            // Datos de ejemplo
            data class Coleccion(val titulo: String, val descripcion: String)

            val listaColecciones = remember {
                mutableStateListOf(
                    Coleccion("Vocabulario Texto 1", "Palabras clave del primer texto del libro Hanyu 1."),
                    Coleccion("Saludos y Presentaciones", "Expresiones básicas para saludar y presentarse."),
                    Coleccion("Familia", "Términos relacionados con miembros de la familia."),
                    Coleccion("Animales", "Nombres comunes de animales."),
                    Coleccion("Colores", "Vocabulario para describir colores y objetos."),
                    Coleccion("Comida y Bebidas", "Palabras relacionadas con comidas y bebidas básicas.")
                )
            }

            var mostrarDialogoEliminar by remember { mutableStateOf(false) }
            var coleccionSeleccionada by remember { mutableStateOf<Coleccion?>(null) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Mis Colecciones de Vocabulario",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF333333)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Lista de colecciones
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(listaColecciones) { coleccion ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8F8)),
                            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = coleccion.titulo,
                                        style = MaterialTheme.typography.titleMedium,
                                        color = Color.Black
                                    )
                                    Text(
                                        text = coleccion.descripcion,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.DarkGray
                                    )
                                }

                                Row {
                                    IconButton(onClick = {
                                        // Navegar a pantalla de revisión (más adelante se implementará)
                                        // navController.navigate("detalleColeccion/${coleccion.titulo}")
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Visibility,
                                            contentDescription = "Revisar",
                                            tint = Color(0xFF1976D2)
                                        )
                                    }

                                    IconButton(onClick = {
                                        // Navegar a pantalla de edición (por implementar)
                                        // navController.navigate("editarColeccion/${coleccion.titulo}")
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Edit,
                                            contentDescription = "Editar",
                                            tint = Color(0xFF43A047)
                                        )
                                    }

                                    IconButton(onClick = {
                                        coleccionSeleccionada = coleccion
                                        mostrarDialogoEliminar = true
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Eliminar",
                                            tint = Color(0xFFD32F2F)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Botón para crear nueva colección
                Button(
                    onClick = {
                        // Navegar a pantalla de creación (por implementar)
                        // navController.navigate("crearColeccion")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEE1842))
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Agregar",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Crear Nueva Colección",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

// Modal de confirmación para eliminar
            if (mostrarDialogoEliminar && coleccionSeleccionada != null) {
                AlertDialog(
                    onDismissRequest = { mostrarDialogoEliminar = false },
                    confirmButton = {
                        TextButton(onClick = {
                            listaColecciones.remove(coleccionSeleccionada)
                            mostrarDialogoEliminar = false
                        }) {
                            Text("Eliminar", color = Color(0xFFD32F2F))
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { mostrarDialogoEliminar = false }) {
                            Text("Cancelar", color = Color.Gray)
                        }
                    },
                    title = {
                        Text(
                            text = "Eliminar Colección",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xFFEE1842)
                        )
                    },
                    text = {
                        Text(
                            text = "¿Estás seguro de que deseas eliminar la colección “${coleccionSeleccionada?.titulo}”? Esta acción no se puede deshacer.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                    },
                    containerColor = Color.White
                )
            }
        }
    }
}
