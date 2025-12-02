package com.example.hanyuhub.ui.profesor

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.hanyuhub.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearVocabularioProfe(
    navController: NavController
) {
    // Permite controlar el foco de los elementos
    val focusManager = LocalFocusManager.current

    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFEE1842),
                    titleContentColor = Color(0xFFFFFFFF)
                ),
                title = {
                    Text(
                        "Crear Vocabularios",
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
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
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
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
                    containerColor = Color(0xFFFFFFFF)
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
                    Image(
                        painter = painterResource(id = R.drawable.vocab),
                        contentDescription = "Vocabulario",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier.height(60.dp),
                    onClick = {  },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFC6C1),
                        contentColor = Color(0xFF721313)
                    )
                ) {
                    Text("Agregar caracteres")
                }

                Spacer(modifier = Modifier.height(20.dp))

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
                )

                Spacer(Modifier.height(8.dp))

                Button(
                    modifier = Modifier.height(60.dp),
                    onClick = {  },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFEE1842),
                        contentColor = Color(0xFFFFFFFF)
                    )
                ) {
                    Text("Guardar vocabulario")
                }
            }
        }
    }
}