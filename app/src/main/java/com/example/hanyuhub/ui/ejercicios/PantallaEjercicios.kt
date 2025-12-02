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
import androidx.compose.material.icons.filled.Games
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaEjercicios(
    navController: NavController,
    nombre: String ,
    apellido: String,
    email: String,
    pass: String,
    curso: String) {

    // Le da función para hacer scroll al topbar.
    // Cuando se hace scroll hacia arriba, la barra se reduce (colapsa), y cuando bajas, se expande de nuevo
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF58078),
                    titleContentColor = Color(0xFF721313)
                ),
                title = {
                    Text("EJERCICIOS", style = MaterialTheme.typography.headlineMedium)
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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFC7E5FD))
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {

            Spacer(modifier = Modifier.height(30.dp))

            // Boton de ejercicios
            OutlinedButton(
                onClick = {  },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(100.dp),
                shape = RoundedCornerShape(20.dp),

                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFF4C99EF),
                    contentColor = Color(0xFF003366),
                )
            ) {
                Icon(Icons.Default.Games,
                    contentDescription = "Ejercicios App",
                    modifier = Modifier.size(40.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Text("Ejercicios App",
                    modifier = Modifier.padding(start = 8.dp),
                    style = TextStyle(
                        color = Color(0xFF003366),
                        fontSize = 22.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedButton(
                onClick = {  },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(100.dp),
                shape = RoundedCornerShape(20.dp),

                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFF4C99EF),
                    contentColor = Color(0xFF003366),
                )
            ) {
                Icon(Icons.Default.Games,
                    contentDescription = "Ejercicios Personalizados",
                    modifier = Modifier.size(40.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Text("Ejercicios Personalizados",
                    modifier = Modifier.padding(start = 8.dp),
                    style = TextStyle(
                        color = Color(0xFF003366),
                        fontSize = 22.sp
                    )
                )
            }
        }
    }
}