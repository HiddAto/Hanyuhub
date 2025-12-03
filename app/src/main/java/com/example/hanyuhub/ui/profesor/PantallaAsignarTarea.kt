package com.example.hanyuhub.ui.profesor

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hanyuhub.model.Tareas
import com.example.hanyuhub.repository.TareaRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAsignarTarea(
    navController: NavController,
    nombre: String,
    apellido: String,
    email: String,
    pass: String,
    curso: String
) {
    val tareaRepo = remember { TareaRepository() }
    val coroutine = rememberCoroutineScope()

    val snackbarHostState = remember { SnackbarHostState() }


    // Permite controlar el foco de los elementos
    val focusManager = LocalFocusManager.current

    // Variables vacías
    var id by remember { mutableStateOf("") }
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var fechaPub by remember { mutableStateOf("") }
    var fechaLim by remember { mutableStateOf("") }
    var contenido by remember { mutableStateOf("") }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFEE1842),
                    titleContentColor = Color(0xFFFFFFFF)
                ),
                title = {
                    Text(
                        "Asignar Tarea",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFEE1842),
                contentColor = Color(0xFFFFFFFF)
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver"
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFFFFFFF))
                .fillMaxSize()
                .clickable(
                    // null interactionSource e indication = null
                    // para que no muestre efecto al tocar
                    indication = null,
                    interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
                ) {
                    focusManager.clearFocus() // Oculta el teclado
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE3E3E3)
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Text(
                        text = "ID:",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF721313)
                    )
                    OutlinedTextField(
                        value = id,
                        onValueChange = { id = it },
                        label = { Text("ID") },
                        singleLine = false,
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(color = Color.Black) // <--- esto cambia el color del texto
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Título:",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF721313)
                    )
                    OutlinedTextField(
                        value = titulo,
                        onValueChange = { titulo = it },
                        label = { Text("Titulo") },
                        singleLine = false,
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(color = Color.Black) // <--- esto cambia el color del texto
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Descripción:",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF721313)
                    )
                    OutlinedTextField(
                        value = descripcion,
                        onValueChange = { descripcion = it },
                        label = { Text("Descripción") },
                        singleLine = false,
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(color = Color.Black) // <--- esto cambia el color del texto
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Fecha de publicación:",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF721313)
                    )
                    OutlinedTextField(
                        value = fechaPub,
                        onValueChange = { fechaPub = it },
                        label = { Text("Fecha de publicación") },
                        singleLine = false,
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(color = Color.Black) // <--- esto cambia el color del texto
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Fecha límite:",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF721313)
                    )
                    OutlinedTextField(
                        value = fechaLim,
                        onValueChange = { fechaLim = it },
                        label = { Text("Fecha límite") },
                        singleLine = false,
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(color = Color.Black) // <--- esto cambia el color del texto
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Contenido:",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF721313)
                    )
                    OutlinedTextField(
                        value = contenido,
                        onValueChange = { contenido = it },
                        label = { Text("Contenido") },
                        singleLine = false,
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(color = Color.Black) // <--- esto cambia el color del texto
                    )
                }
                Spacer(Modifier.height(12.dp))
            }
            Spacer(Modifier.height(8.dp))

            // Botón Crear
            Button(
                onClick = {
                    coroutine.launch {
                        try {
                            val tarea = Tareas(
                                id = id,
                                titulo = titulo,
                                descripcion = descripcion,
                                fechaPub = fechaPub,
                                fechaLim = fechaLim,
                                contenido = contenido
                            )

                            val creada = tareaRepo.crearTarea(tarea)

                            if (creada != null) {
                                // Muestra mensaje
                                snackbarHostState.showSnackbar(
                                    message = "Tarea creada correctamente",
                                    duration = SnackbarDuration.Short
                                )

                                // Espera
                                delay(2000)

                                // Vuelve atras
                                navController.popBackStack()

                            } else {
                                Log.e("PantallaAsignarTarea", "Error creando tarea")
                            }

                        } catch (e: Exception) {
                            Log.e("PantallaAsignarTarea", "Excepción creando tarea", e)
                        }
                    }
                },
                modifier = Modifier
                    .height(65.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFDE2910),
                    contentColor = Color.White
                ),
                border = BorderStroke(2.dp, Color(0xFFFFD0CC)),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("CREAR TAREA")
            }



            Spacer(modifier = Modifier.height(150.dp))
        }
    }
}