package com.example.hanyuhub.ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Group
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaLogin(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFDE2910),
                contentColor = Color(0xFFFFFFFF)
            ) {
                IconButton(onClick = { navController.navigate("start") }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver atrás"
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFFFFFFF))
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen de login
            Box(
                modifier = Modifier
                    .size(170.dp)
                    .background(Color(0xFFFFFFFF))
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Group,
                    contentDescription = "Perfil Usuario",
                    modifier = Modifier.size(150.dp),
                    tint = Color(0xFFFFC107)
                )
            }

            // Espacio
            Spacer(modifier = Modifier.size(24.dp))

            // Botón para ir al Login
            Button(
                onClick = { navController.navigate("loginAlumno") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFDE2910),
                    contentColor = Color(0xFFFFFFFF)
                ),
                border = BorderStroke(2.dp, Color(0xFFFFD0CC))
            ) {
                Text(text = "INICIAR SESIÓN COMO ALUMNO")
            }

            Spacer(modifier = Modifier.height(25.dp))

            // Botón para ir al Registro
            Button(
                onClick = { navController.navigate("loginProfesor") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFDE2910),
                    contentColor = Color(0xFFFFFFFF)
                ),
                border = BorderStroke(2.dp, Color(0xFFFFD0CC))
            ) {
                Text(text = "INICIAR SESIÓN COMO PROFESOR")
            }
        }
    }
}

