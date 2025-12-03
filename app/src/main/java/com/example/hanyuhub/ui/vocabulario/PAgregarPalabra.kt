package com.example.hanyuhub.ui.vocabulario

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hanyuhub.model.Palabra
import com.example.hanyuhub.viewmodel.ColeccionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PAgregarPalabra(
    navController: NavController,
    email: String,
    nombre: String,
    apellido: String,
    pass: String,
    curso: String,
    idColeccion: Long,
    nombreColeccion: String,
    viewModel: ColeccionViewModel = viewModel()
) {
    var hanzi by remember { mutableStateOf("") }
    var pinyin by remember { mutableStateOf("") }
    var significado by remember { mutableStateOf("") }

    var camposInvalidos by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF58078),
                    titleContentColor = Color(0xFF721313)
                ),
                title = {
                    Text("Agregar una nueva palabra a la colección: {$nombreColeccion}", style = MaterialTheme.typography.headlineMedium)
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // CAMPO HANZI
            OutlinedTextField(
                value = hanzi,
                onValueChange = { hanzi = it },
                label = { Text("Hanzi (汉字)") },
                isError = camposInvalidos && hanzi.isBlank(),
                modifier = Modifier.fillMaxWidth()
            )

            // CAMPO PINYIN
            OutlinedTextField(
                value = pinyin,
                onValueChange = { pinyin = it },
                label = { Text("Pinyin") },
                isError = camposInvalidos && pinyin.isBlank(),
                modifier = Modifier.fillMaxWidth()
            )

            // CAMPO SIGNIFICADO
            OutlinedTextField(
                value = significado,
                onValueChange = { significado = it },
                label = { Text("Significado") },
                isError = camposInvalidos && significado.isBlank(),
                modifier = Modifier.fillMaxWidth()
            )

            // BOTÓN GUARDAR
            Button(
                onClick = {
                    if (hanzi.isBlank() || pinyin.isBlank() || significado.isBlank()) {
                        camposInvalidos = true
                    } else {
                        val nueva = Palabra(
                            id = null,
                            hanzi = hanzi,
                            pinyin = pinyin,
                            significado = significado
                        )

                        viewModel.agregarPalabra(idColeccion, nueva) {
                            navController.popBackStack() // Vuelve a la pantalla detalle
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF721313),
                    contentColor = Color.White
                )
            ) {
                Text("Guardar palabra")
            }
        }
    }
}