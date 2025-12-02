package com.example.hanyuhub.ui.vocabulario

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hanyuhub.viewmodel.ColeccionViewModel
import kotlin.String

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCrearColeccion(
    navController: NavController,
    mail: String,
    viewModel: ColeccionViewModel = viewModel(),
    nombre: String ,
    apellido: String,
    email: String,
    pass: String,
    curso: String) {

    // Le da función para hacer scroll al topbar.
    // Cuando se hace scroll hacia arriba, la barra se reduce (colapsa), y cuando bajas, se expande de nuevo
    //val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    //var nombreColeccion by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF58078),
                    titleContentColor = Color(0xFF721313)
                ),
                title = {
                    Text("VOCABULARIOS", style = MaterialTheme.typography.headlineMedium)
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
        // Estado del campo de texto
        var nombreColeccion by remember { mutableStateOf("") }
        // Estado para validar si el campo está vacío al intentar crear
        var showError by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
            // 1. Color de fondo más neutro y acorde a Material 3
                .background(Color.White)
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 24.dp) // Añadimos padding general
                .verticalScroll(rememberScrollState()),
            // 2. Espaciado entre elementos más consistente
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Centramos el botón y el título
        ) {

            // 3. Título con mejor estilo
            Text(
                text = "Crear nueva colección",
                style = MaterialTheme.typography.headlineMedium, // Estilo de titular grande
                color = Color.DarkGray,
                modifier = Modifier.fillMaxWidth() // Asegura que el título se alinee a la izquierda
            )

            Spacer(modifier = Modifier.height(8.dp)) // Espacio adicional después del título

            // 4. Campo de texto con estilo Outline y validación básica
            OutlinedTextField(
                value = nombreColeccion,
                onValueChange = {
                    nombreColeccion = it
                    showError = false // Oculta el error al empezar a escribir
                },
                label = { Text("Nombre de la colección") },
                placeholder = { Text("Ej: Paseando por Beijing") },
                isError = showError, // Muestra el error si es true
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { /* Opcional: enfocar el botón */ }),
                modifier = Modifier.fillMaxWidth()
            )

            // Mensaje de error (solo si el campo está vacío)
            if (showError) {
                Text(
                    text = "El nombre de la colección no puede estar vacío.",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(8.dp)) // Espacio antes del botón

            // 5. Botón principal con validación
            Button(
                onClick = {
                    if (nombreColeccion.isBlank()) {
                        showError = true // Muestra error si el campo está vacío
                    } else {
                        viewModel.crearColeccion(nombreColeccion, mail) {
                            navController.popBackStack() // Volver atrás al crear
                        }
                    }
                },
                // Deshabilitar el botón si el campo está vacío (opcional, pero mejora UX)
                enabled = nombreColeccion.isNotBlank(),
                modifier = Modifier.fillMaxWidth() // El botón ocupa todo el ancho
            ) {
                Text(
                    text = "Crear colección",
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}
