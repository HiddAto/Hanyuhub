package com.example.hanyuhub.ui.ejercicios

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hanyuhub.viewmodel.ColeccionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchHS(
    navController: NavController,
    email: String,
    nombre: String,
    apellido: String,
    pass: String,
    curso: String,
    viewModel: ColeccionViewModel = viewModel()
) {

    val colecciones by viewModel.colecciones.collectAsState()

    // Cargar colecciones del usuario actual
    LaunchedEffect(email) {
        viewModel.cargarColecciones(email)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF58078),
                    titleContentColor = Color(0xFF721313)
                ),
                title = { Text("Elige una Colección", style = MaterialTheme.typography.headlineMedium) }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFF58078),
                contentColor = Color(0xFF721313)
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack() // vuelve a PantallaEjercicios
                    }
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                }

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = {
                        navController.navigate("perfilAlumno/$nombre/$apellido/$email/$pass/$curso")
                    }
                ) {
                    Icon(
                        Icons.Filled.AccountCircle,
                        contentDescription = "Perfil",
                        modifier = Modifier.size(36.dp)
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
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Selecciona el vocabulario para jugar:",
                modifier = Modifier.padding(start = 16.dp),
                color = Color(0xFF003366),
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // BOTONES DE COLECCIONES
            colecciones.forEach { coleccion ->

                OutlinedButton(
                    onClick = {
                        val idColeccion = coleccion.id
                        navController.navigate(
                            "JugarHS/$idColeccion/$email/$nombre/$apellido/$pass/$curso"
                        )
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth()
                        .height(80.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color(0xFF4C99EF),
                        contentColor = Color(0xFF003366),
                    )
                ) {
                    Icon(
                        Icons.Default.CollectionsBookmark,
                        contentDescription = "Colección",
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        coleccion.nombre,
                        fontSize = 22.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}