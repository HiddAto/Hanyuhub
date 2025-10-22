package com.example.hanyuhub.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hanyuhub.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaRegistro(navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var passSec by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(false) }
    val isEmailValido = remember(email) {
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    var showEmailVacio by remember { mutableStateOf(false) }
    var showPasswordVacio by remember { mutableStateOf(false) }
    var showNombreVacio by remember { mutableStateOf(false) }
    var showApellidoVacio by remember { mutableStateOf(false) }
    var showPassSecVacio by remember { mutableStateOf(false) }
    var showPasswordsDif by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

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
                        contentDescription = "Volver al inicio"
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
                .padding(innerPadding)
                .clickable(
                    // null interactionSource e indication = null
                    // para que no muestre efecto al tocar
                    indication = null,
                    interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
                ) {
                    focusManager.clearFocus() // Oculta el teclado
                }
        ) {
            // Título inicial
            Text(
                text = "Registrarse",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Imagen de logo
            Image(
                painter = painterResource(R.drawable.ic_login),
                contentDescription = "Logo de login",
                modifier = Modifier.size(100.dp)
            )

            // Campo para el nombre
            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    nombre = it
                    showNombreVacio = false },
                label = { Text("Nombre (*)") },
                // Existe el error si el email no es válido y no está vacío
                isError = showNombreVacio,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                // Se genera un texto de error por debajo
                supportingText = {
                    when {
                        showNombreVacio -> Text("Ingrese su nombre")
                    }
                }
            )

            // Campo para el apellido
            OutlinedTextField(
                value = apellido,
                onValueChange = {
                    apellido = it
                    showApellidoVacio = false },
                label = { Text("Apellido (*)") },
                // Existe el error si el email no es válido y no está vacío
                isError = showApellidoVacio,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                // Se genera un texto de error por debajo
                supportingText = {
                    when {
                        showApellidoVacio -> Text("Ingrese su apellido")
                    }
                }
            )


            // Campo para el correo
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    showEmailVacio = false },
                label = { Text("Correo (*)") },
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
                    showPasswordVacio = false
                    showPasswordsDif = false },
                label = { Text("Contraseña (*)") },
                isError = showPasswordVacio,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                // Cambia para mostrar o ocultar la contraseña
                visualTransformation = if (checked) VisualTransformation.None else PasswordVisualTransformation(),
                supportingText = {
                    if (showPasswordVacio) Text("Ingrese su contraseña")
                }
            )

            // Campo para la contraseña repetida
            OutlinedTextField(
                value = passSec,
                onValueChange = {
                    passSec = it
                    showPassSecVacio = false
                    showPasswordsDif = false },
                label = { Text("Repita la contraseña (*)") },
                isError = showPassSecVacio || showPasswordsDif,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                supportingText = {
                    when {
                        showPassSecVacio -> Text("Ingrese la contraseña")
                        showPasswordsDif -> Text("Las contraseñas no coinciden")
                    }
                }
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        checked = !checked // Al hacer clic en cualquier parte del Row, se alterna el valor
                    }
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = { checked = it },
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
                    showNombreVacio = nombre.isBlank()
                    showApellidoVacio = apellido.isBlank()
                    showPassSecVacio = passSec.isBlank()

                    showPasswordsDif = pass != passSec && passSec.isNotBlank()

                    // Si todo esta correcto se ingresa
                    if (!showEmailVacio && !showPasswordVacio && isEmailValido
                        && !showNombreVacio && !showApellidoVacio && !showPassSecVacio
                        &&!showPasswordsDif) {
                        navController.navigate("homeAlumno/$nombre/$apellido/$email/$pass")
                    }
                },
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("REGISTRARSE")
            }
        }
    }
}