package com.example.hanyuhub.ui.apunte


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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.hanyuhub.model.Apuntes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaApuntes(
    navController: NavController,
    nombre: String,
    apellido: String,
    email: String,
    pass: String,
    curso: String
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    // Data Dummy
    val apuntes = listOf(
        Apuntes(
            "Saludos básicos en chino",
            "Aprender palabras y frases como ni hao, buenos días, adiós.",
            "05 Oct 2025",
            "Aliquam aliquet interdum lorem ut consequat. Cras sit amet porttitor."
        ),
        Apuntes(
            "Pinyin y pronunciación",
            "Explicación de los tonos, pronunciación correcta y uso del pinyin para leer caracteres.",
            "08 Oct 2025",
            "Aliquam aliquet interdum lorem ut consequat. Cras sit amet porttitor."
        ),
        Apuntes(
            "Números en chino",
            "Estudio de los números del 1 al 100 en chino y sus usos.",
            "10 Oct 2025",
            "Aliquam aliquet interdum lorem ut consequat. Cras sit amet porttitor."
        ),
        Apuntes(
            "Radicales más comunes",
            "Lista de los radicales más usados en caracteres chinos y su significado.",
            "12 Oct 2025",
            "Aliquam aliquet interdum lorem ut consequat. Cras sit amet porttitor."
        ),
        Apuntes(
            "Construcción de frases",
            "Estructura básica de sujeto + verbo + objeto en chino.",
            "14 Oct 2025",
            "Aliquam aliquet interdum lorem ut consequat. Cras sit amet porttitor."
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF58078),
                    titleContentColor = Color(0xFF721313)
                ),
                title = {
                    Text("MIS APUNTES", style = MaterialTheme.typography.headlineMedium)
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFF58078),
                contentColor = Color(0xFF721313)
            ) {
                // Botón de volver
                IconButton(onClick = {
                    navController.navigate("homeAlumno/$nombre/$apellido/$email/$pass/$curso")
                }) {
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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("crearApunte") },
                containerColor = Color(0xFFFFC6C1),
                contentColor = Color(0xFF4F0606)
            ) {
                Row (modifier = Modifier.padding(5.dp)) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                    Text("Crear Apunte")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFC7E5FD))
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(20.dp),

        ) {
            Spacer(modifier = Modifier.height(10.dp))

            apuntes.forEach { apunte ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFE3DF)
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(20.dp)) {
                        Text(
                            "Título: ${apunte.titulo}",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color(0xFF721313)
                        )

                        Spacer(Modifier.height(6.dp))

                        Text(
                            "Descripción: ${apunte.descripcion}",
                            color = Color(0xFF4F0606)
                        )

                        Spacer(Modifier.height(6.dp))

                        Text(
                            "Fecha: ${apunte.fecha}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF8A3838)
                        )

                        Spacer(Modifier.height(12.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Button(
                                onClick = { navController.navigate("verApunte") },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFFFC6C1),
                                    contentColor = Color(0xFF4F0606)
                                ),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Ver apunte")
                            }

                            Button(
                                onClick = { navController.navigate("editarApunte") },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFF58078),
                                    contentColor = Color(0xFF721313)
                                ),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Editar")
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}