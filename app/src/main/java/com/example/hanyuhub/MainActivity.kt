package com.example.hanyuhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hanyuhub.ui.home.PantallaHomeAlumno
import com.example.hanyuhub.ui.home.PantallaHomeProfesor
import com.example.hanyuhub.ui.theme.HanyuHubTheme

import com.example.hanyuhub.ui.login.PantallaLogin
import com.example.hanyuhub.ui.login.PantallaLoginAlumno
import com.example.hanyuhub.ui.login.PantallaLoginProfesor
import com.example.hanyuhub.ui.perfil.PantallaPerfilAlumno
import com.example.hanyuhub.ui.perfil.PantallaPerfilProfesor
import com.example.hanyuhub.ui.register.PantallaRegistro
import com.example.hanyuhub.ui.start.PantallaInicio

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HanyuHubTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(){
    // Controlador de navegacion. Permite viajar de una pantalla a otra
    val navController = rememberNavController()
    // NavHost tiene las pantallas a las cuales se pueda navegar
    NavHost(navController = navController,
        startDestination = "start") {
        composable ("login") { PantallaLogin(navController) }
        composable("start") { PantallaInicio(navController) }
        composable("registro") { PantallaRegistro(navController) }
        composable("loginProfesor") { PantallaLoginProfesor(navController) }
        composable("loginAlumno") { PantallaLoginAlumno(navController) }
        composable(
            "homeAlumno/{nombre}/{apellido}/{email}/{pass}",
            arguments = listOf(
                navArgument("nombre"){ type = NavType.StringType },
                navArgument("apellido"){ type = NavType.StringType },
                navArgument("email"){ type = NavType.StringType },
                navArgument("pass"){ type = NavType.StringType }
            )
            ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre").orEmpty()
            val apellido = backStackEntry.arguments?.getString("apellido").orEmpty()
            val email = backStackEntry.arguments?.getString("email").orEmpty()
            val pass = backStackEntry.arguments?.getString("pass").orEmpty()
            PantallaHomeAlumno(navController, nombre, apellido, email, pass)
        }
        composable(
            "perfilAlumno/{nombre}/{apellido}/{email}/{pass}",
            arguments = listOf(
                navArgument("nombre"){ type = NavType.StringType },
                navArgument("apellido"){ type = NavType.StringType },
                navArgument("email"){ type = NavType.StringType },
                navArgument("pass"){ type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre").orEmpty()
            val apellido = backStackEntry.arguments?.getString("apellido").orEmpty()
            val email = backStackEntry.arguments?.getString("email").orEmpty()
            val pass = backStackEntry.arguments?.getString("pass").orEmpty()
            PantallaPerfilAlumno(navController, nombre, apellido, email, pass)
        }
        composable(
            "homeProfesor/{nombre}/{apellido}/{email}/{pass}",
            arguments = listOf(
                navArgument("nombre"){ type = NavType.StringType },
                navArgument("apellido"){ type = NavType.StringType },
                navArgument("email"){ type = NavType.StringType },
                navArgument("pass"){ type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre").orEmpty()
            val apellido = backStackEntry.arguments?.getString("apellido").orEmpty()
            val email = backStackEntry.arguments?.getString("email").orEmpty()
            val pass = backStackEntry.arguments?.getString("pass").orEmpty()
            PantallaHomeProfesor(navController, nombre, apellido, email, pass)
        }
        composable(
            "perfilProfesor/{nombre}/{apellido}/{email}/{pass}",
            arguments = listOf(
                navArgument("nombre"){ type = NavType.StringType },
                navArgument("apellido"){ type = NavType.StringType },
                navArgument("email"){ type = NavType.StringType },
                navArgument("pass"){ type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre").orEmpty()
            val apellido = backStackEntry.arguments?.getString("apellido").orEmpty()
            val email = backStackEntry.arguments?.getString("email").orEmpty()
            val pass = backStackEntry.arguments?.getString("pass").orEmpty()
            PantallaPerfilProfesor(navController, nombre, apellido, email, pass)
        }
    }
}