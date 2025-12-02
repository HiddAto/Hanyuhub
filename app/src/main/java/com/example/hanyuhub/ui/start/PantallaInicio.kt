package com.example.hanyuhub.ui.start

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hanyuhub.R

@Composable
fun PantallaInicio(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen de bienvenida
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFFFFF)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_app),
                contentDescription = "Logo HanyuHub",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                contentScale = ContentScale.Fit
            )
        }

        // Espacio
        Spacer(modifier = Modifier.size(24.dp))

        // Botón para ir al Login
        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(75.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFDE2910),
                contentColor = Color(0xFFFFFFFF)
            ),
            border = BorderStroke(2.dp, Color(0xFFFFD0CC))
        ) {
            Text(text = "INICIAR SESIÓN")
        }

        Spacer(modifier = Modifier.height(25.dp))

        // Botón para ir al Registro
        Button(
            onClick = { navController.navigate("registro") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(75.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFDE2910),
                contentColor = Color(0xFFFFFFFF)
            ),
            border = BorderStroke(2.dp, Color(0xFFFFD0CC))
        ) {
            Text(text = "CREAR CUENTA")
        }

        Spacer(modifier = Modifier.height(25.dp))

        // BOTON TEST
        /*Button(
            onClick = { navController.navigate("homeAlumno/NombreTest/ApellidoTest/email/rgaeer43143/A-2") },
            modifier = Modifier
                .height(55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFDE2910),
                contentColor = Color(0xFFFFFFFF)
            ),
            border = BorderStroke(2.dp, Color(0xFFFFD0CC))
        ) {
            Text("INGRESAR ALUMNO TEST")
        }

        Spacer(modifier = Modifier.height(25.dp))

        // BOTON TEST
        Button(
            onClick = { navController.navigate("homeProfesor/NombreTest/ApellidoTest/email/r9vh398hv3/A-5") },
            modifier = Modifier
                .height(55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFDE2910),
                contentColor = Color(0xFFFFFFFF)
            ),
            border = BorderStroke(2.dp, Color(0xFFFFD0CC))
        ) {
            Text("INGRESAR PROFESOR TEST")
        }
         */
    }
}