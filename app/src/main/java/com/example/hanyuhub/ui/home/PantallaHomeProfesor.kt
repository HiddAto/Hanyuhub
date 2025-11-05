package com.example.hanyuhub.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hanyuhub.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaHomeProfesor(
    navController: NavController,
    nombre: String ,
    apellido: String,
    email: String,
    pass: String,
    curso: String) {
    // https://m3.material.io/components
    val imagenes = listOf(
        R.drawable.laoshi_1
    )
    // Le da función para hacer scroll al topbar.
    // Cuando se hace scroll hacia arriba, la barra se reduce (colapsa), y cuando bajas, se expande de nuevo
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    var imagenActual by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000) // 3 segundos
            imagenActual = if (imagenActual + 1 < imagenes.size) imagenActual + 1 else 0
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color.White,
        topBar = {
            LargeTopAppBar(
                colors = topAppBarColors(
                    containerColor = Color(0xFFEE1842),
                    scrolledContainerColor = Color(0xFFEE1842),
                    titleContentColor = Color(0xFFFFFFFF)
                ),
                title = {
                    Text(
                        "¡你好 $nombre $apellido!",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFEE1842),
                contentColor = Color(0xFFFFFFFF)
            ) {
                // Botón de volver
                IconButton(onClick = { navController.navigate("start") }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver"
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                // Botón de perfil
                IconButton(
                    onClick = {
                        navController.navigate("perfilProfesor/$nombre/$apellido/$email/$pass/$curso")
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
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Image(
                painter = painterResource(id = imagenes[imagenActual]),
                contentDescription = "Imagen rotatoria",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(200.dp)
            )

            // Boton de Mis Apuntes
            OutlinedButton(
                onClick = { navController.navigate("misCursosProfesor") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(100.dp),
                shape = RoundedCornerShape(20.dp),

                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFF4C99EF),
                    contentColor = Color(0xFFFFFFFF),
                )
            ) {
                Icon(Icons.Default.CollectionsBookmark,
                    contentDescription = "Add",
                    modifier = Modifier.size(40.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Text("Mis cursos",
                    modifier = Modifier.padding(start = 8.dp),
                    style = TextStyle(
                        color = Color(0xFFFFFFFF),
                        fontSize = 22.sp,
                        shadow = Shadow(
                            color = Color.Black.copy(alpha = 0.4f), // Color y transparencia de la sombra
                            offset = Offset(2f, 2f),                // Desplazamiento (x, y)
                            blurRadius = 4f                         // Difuminado
                        )
                    )
                )
            }
            /*
            OutlinedButton(
                onClick = {  },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(100.dp),
                shape = RoundedCornerShape(20.dp),

                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFFA5C286),
                    contentColor = Color(0xFF39540F),
                )
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.Assignment,
                    contentDescription = "Add",
                    modifier = Modifier.size(40.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Text("Mis tareas",
                    modifier = Modifier.padding(start = 8.dp),
                    style = TextStyle(
                        color = Color(0xFF39540F),
                        fontSize = 22.sp
                    )
                )
            }

            OutlinedButton(
                onClick = {  },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(100.dp),
                shape = RoundedCornerShape(20.dp),

                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFF8C73B4),
                    contentColor = Color(0xFF200C44),
                )
            ) {
                Icon(Icons.Default.Games,
                    contentDescription = "Add",
                    modifier = Modifier.size(40.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Text("Ejercicios",
                    modifier = Modifier.padding(start = 8.dp),
                    style = TextStyle(
                        color = Color(0xFF200C44),
                        fontSize = 22.sp
                    )
                )
            }*/

            OutlinedButton(
                onClick = { navController.navigate("vocabulariosProfesor") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(100.dp),
                shape = RoundedCornerShape(20.dp),

                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFFF38B84),
                    contentColor = Color(0xFFFFFFFF),

                )
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.MenuBook,
                    contentDescription = "Add",
                    modifier = Modifier.size(40.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Text("Vocabulario",
                    modifier = Modifier.padding(start = 8.dp),
                    style = TextStyle(
                        color = Color(0xFFFFFFFF),
                        fontSize = 22.sp,
                        shadow = Shadow(
                            color = Color.Black.copy(alpha = 0.4f), // Color y transparencia de la sombra
                            offset = Offset(2f, 2f),                // Desplazamiento (x, y)
                            blurRadius = 4f                         // Difuminado
                        )
                    )
                )
            }
        }
    }
}