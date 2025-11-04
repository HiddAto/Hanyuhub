package com.example.hanyuhub.ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hanyuhub.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaLogin(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFF58078),
                contentColor = Color(0xFF721313)
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
                .background(Color(0xFFF5E9E8))
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen de login
            Box(
                modifier = Modifier
                    .size(170.dp)
                    .background(Color(0xFFF5E9E8))
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_login),
                    contentDescription = "Logo Inicio Sesion",
                    modifier = Modifier.fillMaxSize(),
                    colorFilter = ColorFilter.tint(Color(0xFF4F0606))
                )
            }

            // Espacio
            androidx.compose.foundation.layout.Spacer(modifier = Modifier.size(24.dp))

            // Botón para ir al Login
            Button(
                onClick = { navController.navigate("loginAlumno") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF58078),
                    contentColor = Color(0xFF4F0606)
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
                    containerColor = Color(0xFFF58078),
                    contentColor = Color(0xFF4F0606)
                ),
                border = BorderStroke(2.dp, Color(0xFFFFD0CC))
            ) {
                Text(text = "INICIAR SESIÓN COMO PROFESOR")
            }
        }
    }
}

