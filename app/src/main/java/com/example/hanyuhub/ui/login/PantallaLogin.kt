package com.example.hanyuhub.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hanyuhub.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaLogin(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(false) }
    val isEmailValido = remember(email) {
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    var showEmailVacio by remember { mutableStateOf(false) }
    var showPasswordVacio by remember { mutableStateOf(false) }



    // Utilizamos Scaffold para la estructura de la pantalla
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                IconButton(onClick = { navController.navigate("start") }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Localized description"
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(innerPadding) // Aplica innerPadding aquí
        ) {
            // Título inicial
            Text(
                text = "Iniciar Sesión",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Imagen de logo
            Image(
                painter = painterResource(R.drawable.ic_login),
                contentDescription = "Logo de login",
                modifier = Modifier.size(100.dp)
            )


            // Campo para el correo
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    showEmailVacio = false },
                label = { Text("Correo") },
                // Existe el error si el email no es válido y no está vacío
                isError = !isEmailValido && email.isNotEmpty() || showEmailVacio,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                // Se genera un texto de error por debajo
                supportingText = {
                    when {
                        showEmailVacio -> Text("Ingrese su correo")
                        !isEmailValido && email.isNotEmpty() -> Text("Correo inválido")
                    }
                }
            )

            // Campo para la contraseña
            OutlinedTextField(
                value = pass,
                onValueChange = {
                    pass = it
                    showPasswordVacio = false },
                label = { Text("Contraseña") },
                isError = showPasswordVacio,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                // Cambia para mostrar o ocultar la contraseña
                visualTransformation = if (checked) VisualTransformation.None else PasswordVisualTransformation(),
                supportingText = {
                    if (showPasswordVacio) Text("Ingrese su contraseña")
                }
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start, // Alinea a la izquierda
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = { checked = it }
                )
                Text("Mostrar contraseña")
            }

            Spacer(modifier = Modifier.height(15.dp))

            // Botón de ingreso
            Button(
                onClick = {
                    // Se revisan si los valores estan vacios
                    showEmailVacio = email.isBlank()
                    showPasswordVacio = pass.isBlank()

                    // Si todo esta correcto se ingresa
                    if (!showEmailVacio && !showPasswordVacio && isEmailValido) {
                        navController.navigate("home/$email")
                    }
                },
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("Ingresar")
            }
        }
    }
}

