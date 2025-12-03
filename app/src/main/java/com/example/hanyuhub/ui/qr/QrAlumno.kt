package com.example.hanyuhub.ui.qr

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import org.json.JSONObject
import android.graphics.Color as AndroidColor
import androidx.core.graphics.set
import androidx.core.graphics.createBitmap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QrAlumnoScreen(
    navController: NavController,
    nombre: String,
    apellido: String,
    email: String,
    pass: String,
    curso: String
) {
    // Tamaño en dp que vamos a mostrar (ajusta si quieres más/menos)
    val displayDp = 400.dp

    // Convertimos dp a px para generar el bitmap EXACTO al que mostraremos
    val bitmapPx = with(LocalDensity.current) { displayDp.toPx().toInt().coerceAtLeast(200) }

    // Detectar si llegaron placeholders literales (ej "{nombre}")
    val hasPlaceholders = listOf(nombre, apellido, email, pass, curso).any { it.contains("{") && it.contains("}") }

    // --- Contenido del QR en JSON (incluye pass) ---
    // Si hay placeholders mostramos un JSON básico vacío y un aviso en pantalla
    val contenidoQR = remember(nombre, apellido, email, pass, curso) {
        if (hasPlaceholders) {
            // JSON 'defensivo' para no romper el generator; el usuario verá un aviso
            JSONObject().apply {
                put("nombre", nombre)
                put("apellido", apellido)
                put("email", email)
                put("pass", pass)
                put("curso", curso)
            }.toString()
        } else {
            JSONObject().apply {
                put("nombre", nombre)
                put("apellido", apellido)
                put("email", email)
                put("pass", pass)
                put("curso", curso)
            }.toString()
        }
    }

    // Log para debugging — ver en Logcat: "QrAlumnoDebug"
    LaunchedEffect(contenidoQR) {
        Log.d("QrAlumnoDebug", "Contenido QR generado: $contenidoQR")
        if (hasPlaceholders) {
            Log.w("QrAlumnoDebug", "Se detectaron placeholders en los parámetros. Revisa la navegación: debes pasar valores reales, no '{nombre}'")
        }
    }

    // Generamos el bitmap exactamente del tamaño en px que se mostrará
    val qrBitmap = remember(contenidoQR, bitmapPx) {
        generateQrCodeReliable(contenidoQR, size = bitmapPx)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF58078),
                    titleContentColor = Color(0xFF721313)
                ),
                title = {
                    Text("Mi QR", style = MaterialTheme.typography.headlineMedium)
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFF58078),
                contentColor = Color(0xFF721313)
            ) {
                Button(
                    onClick = { navController.navigate("homeAlumno/$nombre/$apellido/$email/$pass/$curso") },
                    modifier = Modifier
                        .height(55.dp)
                        .padding(2.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF5E9E8),
                        contentColor = Color(0xFF4F0606)
                    ),
                    border = BorderStroke(2.dp, Color(0xFFFFD0CC))
                ) {
                    Text("HOME")
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "$nombre $apellido",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 20.dp),
                color = Color(0xFF4F0606)
            )

            if (qrBitmap != null) {
                Image(
                    bitmap = qrBitmap.asImageBitmap(),
                    contentDescription = "QR Alumno",
                    modifier = Modifier.size(displayDp),
                    contentScale = ContentScale.Fit
                )
            } else {
                Text("Error al generar QR.")
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

fun generateQrCodeReliable(text: String, size: Int = 600): Bitmap? {
    return try {
        val hints = mapOf(
            EncodeHintType.MARGIN to 1,
            EncodeHintType.ERROR_CORRECTION to ErrorCorrectionLevel.L
        )

        val bitMatrix: BitMatrix = MultiFormatWriter().encode(
            text,
            BarcodeFormat.QR_CODE,
            size,
            size,
            hints
        )

        val width = bitMatrix.width
        val height = bitMatrix.height
        val bmp = createBitmap(width, height)

        for (x in 0 until width) {
            for (y in 0 until height) {
                bmp[x, y] = if (bitMatrix[x, y]) AndroidColor.BLACK else AndroidColor.WHITE
            }
        }
        bmp
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

