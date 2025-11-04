package com.example.hanyuhub.ui.login

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hanyuhub.R
import com.example.hanyuhub.repository.UsuarioRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaLoginAlumno(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(false) }
    val isEmailValido = remember(email) {
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    var showEmailVacio by remember { mutableStateOf(false) }
    var showPasswordVacio by remember { mutableStateOf(false) }

    //Variables para la base de datos
    val usuarioRepository = UsuarioRepository()
    val context = LocalContext.current

    // Permite controlar el foco de los elementos
    val focusManager = LocalFocusManager.current

    // Utilizamos Scaffold para la estructura de la pantalla
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFF58078),
                contentColor = Color(0xFF721313)
            ) {
                IconButton(onClick = { navController.navigate("login") }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver a Seleccion Login"
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
                .background(Color(0xFFF5E9E8))
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
                text = "Iniciar Sesión",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Image(
                painter = painterResource(R.drawable.alumno1),
                contentDescription = "Logo de login",
                modifier = Modifier.size(150.dp)
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
                    // CoroutineScope para llamar al servicio
                    CoroutineScope(Dispatchers.Main).launch {
                        // Llamada al servicio y guarda la respuesta en usuario
                        val usuario = usuarioRepository.login(email, pass)
                        // Se revisan si los valores estan vacios
                        showEmailVacio = email.isBlank()
                        showPasswordVacio = pass.isBlank()

                        // Comprobación de los campos
                        if (!showEmailVacio && !showPasswordVacio && isEmailValido) {

                            //Si el usuario y contraseña son válidos se ingresa a la pantalla del alumno
                            if (usuario != null) {
                                navController.navigate("homeAlumno/NombreTest/ApellidoTest/$email/$pass/A-2")
                            } else {
                                Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }
                },
                modifier = Modifier
                    .height(65.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF58078),
                    contentColor = Color(0xFF4F0606)
                ),
                border = BorderStroke(2.dp, Color(0xFFFFD0CC)),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("INGRESAR")
            }
        }
    }
}