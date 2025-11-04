package com.example.hanyuhub.ui.register

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
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
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.hanyuhub.model.UsuarioDto
import com.example.hanyuhub.repository.UsuarioRepository
import kotlinx.coroutines.launch

@Composable
fun PantallaRegistro(navController: NavController) {

    // Campos del formulario
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var passSec by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(false) }

    //Validaciones
    val isEmailValido = remember(email) {
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    var showEmailVacio by remember { mutableStateOf(false) }
    var showPasswordVacio by remember { mutableStateOf(false) }
    var showNombreVacio by remember { mutableStateOf(false) }
    var showApellidoVacio by remember { mutableStateOf(false) }
    var showPassSecVacio by remember { mutableStateOf(false) }
    var showPasswordsDif by remember { mutableStateOf(false) }
    var mensaje by remember { mutableStateOf("") }

    // Para ocultar teclado
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val usuarioRepository = remember { UsuarioRepository() }

    // Utilizamos Scaffold para la estructura de la pantalla
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFF58078),
                contentColor = Color(0xFF721313)
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
                .background(Color(0xFFF5E9E8))
                .padding(16.dp)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .clickable(
                    // null interactionSource e indication = null
                    // para que no muestre efecto al tocar
                    indication = null,
                    interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
                ) {
                    // Oculta el teclado
                    focusManager.clearFocus()
                }
        ) {
            // Título inicial
            Text(
                text = "Registrarse",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Imagen de registro
            Box(
                modifier = Modifier
                    .size(170.dp)
                    .background(Color(0xFFF5E9E8))
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_login),
                    contentDescription = "Logo Registro",
                    modifier = Modifier.fillMaxSize(),
                    colorFilter = ColorFilter.tint(Color(0xFF4F0606))
                )
            }

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
                        // Al hacer clic en cualquier parte del Row, se alterna el valor
                        checked = !checked
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
                    // Validaciones locales
                    showEmailVacio = email.isBlank()
                    showPasswordVacio = pass.isBlank()
                    showNombreVacio = nombre.isBlank()
                    showApellidoVacio = apellido.isBlank()
                    showPassSecVacio = passSec.isBlank()

                    showPasswordsDif = pass != passSec && passSec.isNotBlank()

                    if (!showEmailVacio && !showPasswordVacio && isEmailValido
                        && !showNombreVacio && !showApellidoVacio && !showPassSecVacio
                        && !showPasswordsDif
                    ) {
                        // Inicia la corrutina para llamadas a API
                        scope.launch {
                            try {
                                // Verifica si el email ya existe
                                val emailExiste = usuarioRepository.validarEmail(email)

                                if (emailExiste) {
                                    // El correo ya está en uso, muestra mensaje de error
                                    mensaje = "El correo electrónico ya está registrado."
                                } else {
                                    // Email disponible, crear UsuarioDto
                                    val nuevoUsuario = UsuarioDto(
                                        nombre = nombre,
                                        apellido = apellido,
                                        mail = email,
                                        pass = pass,
                                        rol = "estudiante"
                                    )

                                    val response = usuarioRepository.registrarUsuario(nuevoUsuario)

                                    if (response.isSuccessful) {
                                        mensaje = "Usuario registrado correctamente"
                                        // Navega a la pantalla principal solo si registro OK
                                        navController.navigate("homeAlumno/$nombre/$apellido/$email/$pass/A-2")
                                    } else {
                                        mensaje = "Error al registrar: ${response.message()}"
                                    }
                                }
                            } catch (e: Exception) {
                                mensaje = "Error de conexión: ${e.localizedMessage}"
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
                Text("REGISTRARSE")
            }
            // Mostramos el mensaje debajo del botón
            if (mensaje.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = mensaje,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}