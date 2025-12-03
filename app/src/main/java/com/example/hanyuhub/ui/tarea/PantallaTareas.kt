package com.example.hanyuhub.ui.tarea

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.hanyuhub.model.Tareas
import com.example.hanyuhub.repository.TareaRepository
import kotlinx.coroutines.launch
import android.util.Log
import androidx.compose.material3.SnackbarDuration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaTareas(
    navController: NavController,
    nombre: String,
    apellido: String,
    email: String,
    pass: String,
    curso: String
) {

    // Repositorio
    val tareaRepo = remember { TareaRepository() }

    // Estados
    val tareas = remember { mutableStateListOf<Tareas>() }
    var isLoading by remember { mutableStateOf(true) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutine = rememberCoroutineScope()

    // Carga todas las tareas al iniciar
    LaunchedEffect(Unit) {
        isLoading = true
        try {
            val lista = tareaRepo.listarTareas()
            tareas.clear()
            lista?.let { tareas.addAll(it) }
        } catch (e: Exception) {
            Log.e("PantallaTareas", "Error cargando tareas", e)
            coroutine.launch {
                snackbarHostState.showSnackbar("Error cargando tareas")
            }
        } finally {
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF58078),
                    titleContentColor = Color(0xFF721313)
                ),
                title = {
                    Text("MIS TAREAS", style = MaterialTheme.typography.headlineMedium)
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFF58078),
                contentColor = Color(0xFF721313)
            ) {
                IconButton(onClick = {
                    navController.navigate("homeAlumno/$nombre/$apellido/$email/$pass/$curso")
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver"
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

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
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFC7E5FD))
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            // Indicador de carga
            if (isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Cargando tareas...", style = MaterialTheme.typography.bodyMedium)
                }
            } else {
                if (tareas.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("No hay tareas disponibles", style = MaterialTheme.typography.bodyMedium)
                    }
                } else {
                    tareas.forEach { tarea ->
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFFFE3DF)
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(Modifier.padding(20.dp)) {
                                Text(
                                    tarea.titulo,
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color(0xFF4F0606)
                                )

                                Spacer(Modifier.height(4.dp))

                                Text(
                                    tarea.descripcion,
                                    color = Color(0xFF4F0606)
                                )

                                Spacer(Modifier.height(12.dp))

                                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                    Text(
                                        "Publicada: ${tarea.fechaPub}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color(0xFF8A3838)
                                    )

                                    Text(
                                        "Fecha Limite: ${tarea.fechaLim}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color(0xFF8A3838)
                                    )
                                }

                                Spacer(Modifier.height(12.dp))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Button(
                                        onClick = {
                                            coroutine.launch {
                                                try {
                                                    val success = tareaRepo.borrarTarea(tarea.id)

                                                    if (success) {
                                                        tareas.remove(tarea)

                                                        snackbarHostState.showSnackbar(
                                                            message = "Tarea completada",
                                                            duration = SnackbarDuration.Short
                                                        )
                                                    } else {
                                                        snackbarHostState.showSnackbar(
                                                            message = "Error al eliminar la tarea",
                                                            duration = SnackbarDuration.Short
                                                        )
                                                    }
                                                } catch (e: Exception) {
                                                    Log.e("PantallaTareas", "Error al borrar tarea", e)
                                                    snackbarHostState.showSnackbar(
                                                        message = "Error inesperado al eliminar",
                                                        duration = SnackbarDuration.Short
                                                    )
                                                }
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFFF58078),
                                            contentColor = Color(0xFF721313)
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth(0.6f),
                                        shape = RoundedCornerShape(6.dp)
                                    ) {
                                        Text("Completar tarea")
                                    }
                                }

                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}
