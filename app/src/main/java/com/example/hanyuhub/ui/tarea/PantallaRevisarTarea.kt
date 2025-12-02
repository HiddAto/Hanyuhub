package com.example.hanyuhub.ui.tarea

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaRevisarTarea(
    navController: NavController
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF58078),
                    titleContentColor = Color(0xFF721313)
                ),
                title = {
                    Text(
                        "Revisión Tarea",
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFF58078),
                contentColor = Color(0xFF721313)
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
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .background(Color(0xFFC7E5FD)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFE3DF)
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Título:",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF721313)
                    )
                    Text(
                        text = "Practicar saludos básicos",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color(0xFF4F0606)
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Descripción:",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF721313)
                    )
                    Text(
                        text = "Graba un audio saludando en chino.",
                        color = Color(0xFF4F0606)
                    )

                    Spacer(Modifier.height(8.dp))


                    Row(horizontalArrangement = Arrangement.spacedBy(60.dp)){
                        Text(
                            "Publicado: 05 Oct 2025",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF8A3838)
                        )

                        Text(
                            "Fecha Limite: 08 Oct 2025",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF8A3838)
                        )
                    }

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Contenido:",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF721313)
                    )
                    Text(
                        text = "Entrega el audio en formato mp3.",
                        color = Color(0xFF4F0606)
                    )

                    Spacer(Modifier.height(8.dp))
                }
            }

            Spacer(Modifier.height(20.dp))

            Button(
                modifier = Modifier.height(60.dp),
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF58078),
                    contentColor = Color(0xFF721313)
                )
            ) {
                Text("Completar tarea")
            }
        }
    }
}
