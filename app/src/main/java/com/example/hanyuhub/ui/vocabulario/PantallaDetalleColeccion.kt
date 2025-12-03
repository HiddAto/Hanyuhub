package com.example.hanyuhub.ui.vocabulario

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
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
fun PantallaDetalleColeccion(
    navController: NavController,
    email: String,
    nombre: String ,
    apellido: String,
    pass: String,
    curso: String,
    idColeccion: Long,
    nombreColeccion: String,
    viewModel: ColeccionViewModel = viewModel()
) {

    val colecciones by viewModel.colecciones.collectAsState()
    val palabras by viewModel.palabras.collectAsState()

    LaunchedEffect(idColeccion) {
        viewModel.cargarPalabras(idColeccion)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF58078),
                    titleContentColor = Color(0xFF721313)
                ),
                title = {
                    Text("Mis Colecciones", style = MaterialTheme.typography.headlineMedium)
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFF58078),
                contentColor = Color(0xFF721313)
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
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(bottom = 16.dp, end = 16.dp)
            ) {

                /* 🔴 FAB: ELIMINAR COLECCIÓN */
                FloatingActionButton(
                    onClick = {
                        viewModel.eliminarColeccion(idColeccion) {
                            navController.popBackStack()  // ← vuelve a pantallaColecciones
                        }
                    },
                    containerColor = Color(0xFFFF8A80),
                    contentColor = Color.White
                ) {
                    Row(modifier = Modifier.padding(5.dp)) {
                        Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("")
                    }
                }

                /* 🟢 FAB: AGREGAR PALABRA */
                FloatingActionButton(
                    onClick = {
                        navController.navigate(
                            "PAgregarPalabra/$email/$nombre/$apellido/$pass/$curso/$idColeccion/$nombreColeccion"
                        )
                    },
                    containerColor = Color(0xFFFFC6C1),
                    contentColor = Color(0xFF4F0606)
                ) {
                    Row(modifier = Modifier.padding(5.dp)) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("")
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                Text(
                    text = "${nombreColeccion}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Black
                )
            }

            items(palabras) { palabra ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF39FAB)) // rosado muy suave
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Columna 1: Hanzi
                        Text(
                            text = palabra.hanzi,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xFF721313),
                            modifier = Modifier.weight(1f)
                        )

                        // Columna 2: Pinyin
                        Text(
                            text = palabra.pinyin,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black,
                            modifier = Modifier.weight(1f)
                        )

                        // Columna 3: Significado
                        Text(
                            text = palabra.significado,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

