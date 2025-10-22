package com.example.hanyuhub.ui.perfil

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPerfilProfesor(
    navController: NavController,
    nombre: String,
    apellido: String,
    email: String,
    pass: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("TU PERFIL", style = MaterialTheme.typography.headlineMedium)
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Button(
                    onClick = { navController.navigate("homeProfesor/$nombre/$apellido/$email/$pass") },
                    modifier = Modifier
                        .height(55.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text("HOME")
                }
            }
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text("Tus datos", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(50.dp))
            Text("Nombre: $nombre $apellido", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(30.dp))
            Text("Correo: $email", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(30.dp))
            Text("Contraseña: $pass", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(30.dp))
            Text("Cursos que enseñas: A-1, C-3", style = MaterialTheme.typography.headlineSmall)
        }
    }
}