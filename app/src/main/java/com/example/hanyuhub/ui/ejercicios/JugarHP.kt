package com.example.hanyuhub.ui.ejercicios

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hanyuhub.viewmodel.ColeccionViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JugarHP(
    navController: NavController,
    idColeccion: Long,
    email: String,
    nombre: String,
    apellido: String,
    pass: String,
    curso: String,
    viewModel: ColeccionViewModel = viewModel()
) {
    val palabras by viewModel.palabras.collectAsState()

    LaunchedEffect(idColeccion) {
        viewModel.cargarPalabras(idColeccion)
    }

    var hanziList by remember(palabras) { mutableStateOf(palabras.map { it.hanzi }.shuffled()) }
    var pinyinList by remember(palabras) { mutableStateOf(palabras.map { it.pinyin }.shuffled()) }

    var seleccionHanzi by remember { mutableStateOf<String?>(null) }
    var seleccionPinyin by remember { mutableStateOf<String?>(null) }

    var mostrarModal by remember { mutableStateOf(false) }
    var modalMensaje by remember { mutableStateOf("") }
    var modalColor by remember { mutableStateOf(Color.Black) }

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF58078),
                    titleContentColor = Color(0xFFFFFFFF)
                ),
                title = { Text("Match: Hanzi = Pinyin") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("MatchHS/$email/$nombre/$apellido/$pass/$curso")
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->

        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {

            // Contenido scrollable
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(Color(0xFFFFFFFF))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (palabras.isEmpty()) {
                    Text("No hay palabras en esta colección 😢")
                    return@Column
                }

                Text(
                    "Selecciona un Hanzi y luego su Pinyin",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF1A1A1A)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    // Columna Hanzi
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "HANZI", fontSize = 18.sp, fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1A1A)
                        )

                        hanziList.forEach { h ->
                            Button(
                                onClick = { seleccionHanzi = h },
                                modifier = Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor =
                                        if (seleccionHanzi == h) Color(0xFFB36C6C) // más oscuro
                                        else Color(0xFFFFE3E3)
                                )
                            ) {
                                Text(h)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Columna Pinyin
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "PINYIN", fontSize = 18.sp, fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1A1A)
                        )

                        pinyinList.forEach { p ->
                            Button(
                                onClick = { seleccionPinyin = p },
                                modifier = Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor =
                                        if (seleccionPinyin == p) Color(0xFF87D78F) // más oscuro
                                        else Color(0xFFE7FFE2)
                                )
                            ) {
                                Text(p)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(80.dp)) // espacio inferior para botón flotante
            }

            // Botón flotante validar
            FloatingActionButton(
                onClick = {
                    if (seleccionHanzi != null && seleccionPinyin != null) {
                        val palabra = palabras.find { it.hanzi == seleccionHanzi }
                        if (palabra?.pinyin == seleccionPinyin) {
                            modalMensaje = "✔ ¡Correcto!"
                            modalColor = Color(0xFF1B5E20)
                            hanziList = hanziList.filterNot { it == seleccionHanzi }
                            pinyinList = pinyinList.filterNot { it == seleccionPinyin }
                        } else {
                            modalMensaje = "✖ Incorrecto"
                            modalColor = Color(0xFFB71C1C)
                        }

                        mostrarModal = true
                        seleccionHanzi = null
                        seleccionPinyin = null

                        scope.launch {
                            delay(1000)
                            mostrarModal = false
                        }
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                containerColor = Color(0xFF4C99EF)
            ) {
                Text("Validar")
            }

            // Modal animado
            AnimatedVisibility(
                visible = mostrarModal,
                enter = fadeIn() + scaleIn(initialScale = 0.7f),
                exit = fadeOut() + scaleOut(targetScale = 0.7f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0x88000000)), // semitransparente
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {
                        Text(
                            text = modalMensaje,
                            color = modalColor,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(32.dp)
                        )
                    }
                }
            }
        }
    }
}
