package com.example.hanyuhub.ui.vocabulario

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hanyuhub.viewmodel.ColeccionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaColecciones(
    navController: NavController,
    mail: String,
    nombre: String ,
    apellido: String,
    email: String,
    pass: String,
    curso: String,
    viewModel: ColeccionViewModel = viewModel()) {

    val colecciones by viewModel.colecciones.collectAsState()

    LaunchedEffect(mail) {
        viewModel.cargarColecciones(mail)
    }

   //val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    //var nombreColeccion by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF58078),
                    titleContentColor = Color(0xFFFFFFFF)
                ),
                title = {
                    Text("Mis Colecciones", style = MaterialTheme.typography.headlineMedium)
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFF58078),
                contentColor = Color(0xFFFFFFFF)
            ) {
                // Botón de volver
                IconButton(onClick = { navController.navigate("homeAlumno/$nombre/$apellido/$email/$pass/$curso") }) {
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
                onClick = { navController.navigate("crearColeccion/$nombre/$apellido/$email/$pass/$curso/$email") },
                containerColor = Color(0xFFE7DDDD),
                contentColor = Color(0xFF4F0606)
            ) {
                Row(modifier = Modifier.padding(5.dp)) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                    Text("Crear Colección")
                }
            }
        }
    ) { innerPadding ->
        LazyColumn( //  Usa LazyColumn como el contenedor principal y único
            modifier = Modifier
                .fillMaxSize() // Ocupa todo el espacio de la pantalla
                .background(Color.White)
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
                items(colecciones) { col ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp) // Espaciado alrededor de la tarjeta
                            .clickable {
                                val nombreColeccion = col.nombre

                                navController.navigate("pantallaDetalleColeccion/$email/$nombre/$apellido/$pass/$curso/${col.id}/${nombreColeccion}")
                            },
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // Añade una sombra/elevación
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF39FAB)) // rosado muy suave
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically // Centra verticalmente el contenido
                        ) {
                            // Nombre del elemento - toma la mayor parte del espacio
                            Text(
                                text = col.nombre,
                                style = MaterialTheme.typography.titleMedium, // Estilo de texto más prominente
                                modifier = Modifier.weight(1f) // Permite que el texto ocupe el espacio restante
                            )

                            // Ícono para indicar que es un elemento navegable
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = "Ver detalles",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant // Tono de color sutil
                            )
                        }
                    }
                }
            }
        }
}
