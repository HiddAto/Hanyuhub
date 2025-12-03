package com.example.hanyuhub.ui.qr

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.json.JSONObject
import com.example.hanyuhub.ui.qr.QrScannerScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QrProfesorScreen(
    navController: NavController,
    nombre: String,
    apellido: String,
    email: String,
    pass: String,
    cursos: String
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },

        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFEE1842),
                    titleContentColor = Color(0xFFFFFFFF)
                ),
                title = {
                    Text("ESCANEAR ALUMNO", style = MaterialTheme.typography.headlineMedium)
                }
            )
        },

        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFEE1842),
                contentColor = Color(0xFFFFFFFF)
            ) {
                Button(
                    onClick = { navController.navigate("homeProfesor/$nombre/$apellido/$email/$pass/$cursos") },
                    modifier = Modifier
                        .height(55.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text("HOME")
                }
            }
        }

    ) { padding ->
        QrScannerScreen(
            onQrScanned = { rawValue ->

                try {
                    val obj = JSONObject(rawValue)

                    val alumnoNombre = obj.optString("nombre")
                    val alumnoApellido = obj.optString("apellido")
                    val alumnoEmail = obj.optString("email")
                    val alumnoPass = obj.optString("pass")
                    val alumnoCurso = obj.optString("curso")

                    navController.navigate("perfilAlumnoQr/$alumnoNombre/$alumnoApellido/$alumnoEmail/$alumnoPass/$alumnoCurso/$nombre/$apellido/$email/$pass/$cursos") {
                        popUpTo("homeProfesor") { inclusive = false }
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("QR inválido o mal formateado.")
                        navController.popBackStack()
                    }
                }
            },

            onClose = {
                navController.popBackStack()
            },

            modifier = Modifier.padding(padding)
        )
    }
}