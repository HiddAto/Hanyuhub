package com.example.hanyuhub.ui.perfil

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPerfilProfesor(
    navController: NavController,
    nombre: String,
    apellido: String,
    email: String,
    pass: String,
    curso: String
) {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFEE1842),
                    titleContentColor = Color(0xFFFFFFFF)
                ),
                title = {
                    Text("MI PERFIL", style = MaterialTheme.typography.headlineMedium)
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFEE1842),
                contentColor = Color(0xFFFFFFFF)
            ) {
                Button(
                    onClick = { navController.navigate("homeProfesor/$nombre/$apellido/$email/$pass/$curso") },
                    modifier = Modifier
                        .height(65.dp)
                        .padding(12.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF5E9E8),
                        contentColor = Color(0xFF312E2E)
                    ),
                    border = BorderStroke(2.dp, Color(0xFFFFD0CC))
                ) {
                    Text("HOME")
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
            Spacer(modifier = Modifier.height(10.dp))
            Column(Modifier
                .align(Alignment.CenterHorizontally)
            ) {
                Text("Mis datos", style = MaterialTheme.typography.headlineMedium, color = Color.DarkGray)
            }

            // Tarjeta de nombre
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF80C5C)
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(Modifier.padding(20.dp)) {
                    Text("Nombre completo", style = MaterialTheme.typography.titleMedium, color = Color.DarkGray)
                    Text("$nombre $apellido", style = MaterialTheme.typography.headlineSmall, color = Color.White) //  color del texto)
                }
            }

            // Tarjeta de correo
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF80C5C)
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(Modifier.padding(20.dp)) {
                    Text("Correo electrónico", style = MaterialTheme.typography.titleMedium, color = Color.DarkGray)
                    Text(email, style = MaterialTheme.typography.headlineSmall, color = Color.White)
                }
            }

            // Tarjeta de contraseña
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF80C5C)
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(Modifier.padding(20.dp)) {
                    Text("Contraseña", style = MaterialTheme.typography.titleMedium, color = Color.DarkGray)
                    Text(pass, style = MaterialTheme.typography.headlineSmall, color = Color.White)
                }
            }

            // Tarjeta del curso
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF80C5C)
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(Modifier.padding(20.dp)) {
                    Text("Curso asignado", style = MaterialTheme.typography.titleMedium, color = Color.DarkGray)
                    Text(curso, style = MaterialTheme.typography.headlineSmall, color = Color.White)
                }
            }
        }
    }
}