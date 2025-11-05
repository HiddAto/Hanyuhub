package com.example.hanyuhub.ui.profesor

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hanyuhub.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisCursosProfesor(
    navController: NavController
) {
    val imagenes = listOf(R.drawable.laoshi_1)

    var imagenActual by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            imagenActual = if (imagenActual + 1 < imagenes.size) imagenActual + 1 else 0
        }
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFEE1842),
                    titleContentColor = Color(0xFFFFFFFF)
                ),
                title = {
                    Text(
                        "Mis Cursos",
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
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = imagenes[imagenActual]),
                contentDescription = "Imagen rotatoria",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(200.dp)
            )

            //  Lista de botones (puedes agregar más fácilmente)
            val botones = listOf(
                "Básico - A" to Color(0xFF4C99EF),
                "Básico - B" to Color(0xFFF38B84),
                "Intermedio - C" to Color(0xFF67C587),
                "Avanzado - A" to Color(0xFFFFC107)
            )

            //  GRID DE BOTONES (2 columnas)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                state = rememberLazyGridState()
            ) {
                items(botones) { (titulo, colorFondo) ->
                    OutlinedButton(
                        onClick = {
                            //Esto es temporal hasta que conectemos esta parte a la BBDD
                            navController.navigate("VistaCursoProfesor")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = colorFondo,
                            contentColor = Color.White
                        )
                    ) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            titulo,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 20.sp,
                                shadow = Shadow(
                                    color = Color.Black.copy(alpha = 0.4f),
                                    offset = Offset(2f, 2f),
                                    blurRadius = 4f
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}
