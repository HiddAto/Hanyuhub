package com.example.hanyuhub

import com.example.hanyuhub.ui.profesor.MisCursosProfesor
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
import com.example.hanyuhub.ui.apunte.PantallaApuntes
import com.example.hanyuhub.ui.apunte.PantallaApuntesDummy
import com.example.hanyuhub.ui.apunte.PantallaCrearApunte
import com.example.hanyuhub.ui.apunte.PantallaEditarApunteDummy
import com.example.hanyuhub.ui.perfil.PantallaPerfilAlumno
import com.example.hanyuhub.ui.perfil.PantallaPerfilProfesor
import com.example.hanyuhub.ui.profesor.PantallaAsignarTarea
import com.example.hanyuhub.ui.profesor.PantallaAsignarVocabulario
import com.example.hanyuhub.ui.profesor.PantallaRevisarTareas
import com.example.hanyuhub.ui.profesor.PantallaVocabularios
import com.example.hanyuhub.ui.profesor.VistaCursoProfesor
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
            "homeAlumno/{nombre}/{apellido}/{email}/{pass}/{curso}",
            arguments = listOf(
                navArgument("nombre"){ type = NavType.StringType },
                navArgument("apellido"){ type = NavType.StringType },
                navArgument("email"){ type = NavType.StringType },
                navArgument("pass"){ type = NavType.StringType },
                navArgument("curso"){ type = NavType.StringType }
            )
        ) { backStackEntry ->
        val nombre = backStackEntry.arguments?.getString("nombre").orEmpty()
        val apellido = backStackEntry.arguments?.getString("apellido").orEmpty()
        val email = backStackEntry.arguments?.getString("email").orEmpty()
        val pass = backStackEntry.arguments?.getString("pass").orEmpty()
        val curso = backStackEntry.arguments?.getString("curso").orEmpty()
        PantallaHomeAlumno(navController, nombre, apellido, email, pass, curso)
        }
        composable(
            "perfilAlumno/{nombre}/{apellido}/{email}/{pass}/{curso}",
            arguments = listOf(
                navArgument("nombre"){ type = NavType.StringType },
                navArgument("apellido"){ type = NavType.StringType },
                navArgument("email"){ type = NavType.StringType },
                navArgument("pass"){ type = NavType.StringType },
                navArgument("curso"){ type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre").orEmpty()
            val apellido = backStackEntry.arguments?.getString("apellido").orEmpty()
            val email = backStackEntry.arguments?.getString("email").orEmpty()
            val pass = backStackEntry.arguments?.getString("pass").orEmpty()
            val curso = backStackEntry.arguments?.getString("curso").orEmpty()
            PantallaPerfilAlumno(navController, nombre, apellido, email, pass, curso)
        }
        composable(
            "homeProfesor/{nombre}/{apellido}/{email}/{pass}/{cursos}",
            arguments = listOf(
                navArgument("nombre"){ type = NavType.StringType },
                navArgument("apellido"){ type = NavType.StringType },
                navArgument("email"){ type = NavType.StringType },
                navArgument("pass"){ type = NavType.StringType },
                navArgument("cursos"){ type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre").orEmpty()
            val apellido = backStackEntry.arguments?.getString("apellido").orEmpty()
            val email = backStackEntry.arguments?.getString("email").orEmpty()
            val pass = backStackEntry.arguments?.getString("pass").orEmpty()
            val cursos = backStackEntry.arguments?.getString("cursos").orEmpty()
            PantallaHomeProfesor(navController, nombre, apellido, email, pass, cursos)
        }
        composable(
            "perfilProfesor/{nombre}/{apellido}/{email}/{pass}/{cursos}",
            arguments = listOf(
                navArgument("nombre"){ type = NavType.StringType },
                navArgument("apellido"){ type = NavType.StringType },
                navArgument("email"){ type = NavType.StringType },
                navArgument("pass"){ type = NavType.StringType },
                navArgument("cursos"){ type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre").orEmpty()
            val apellido = backStackEntry.arguments?.getString("apellido").orEmpty()
            val email = backStackEntry.arguments?.getString("email").orEmpty()
            val pass = backStackEntry.arguments?.getString("pass").orEmpty()
            val cursos = backStackEntry.arguments?.getString("cursos").orEmpty()
            PantallaPerfilProfesor(navController, nombre, apellido, email, pass, cursos)
        }

        composable(
            "apuntes/{nombre}/{apellido}/{email}/{pass}/{cursos}",
            arguments = listOf(
                navArgument("nombre"){ type = NavType.StringType },
                navArgument("apellido"){ type = NavType.StringType },
                navArgument("email"){ type = NavType.StringType },
                navArgument("pass"){ type = NavType.StringType },
                navArgument("cursos"){ type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre").orEmpty()
            val apellido = backStackEntry.arguments?.getString("apellido").orEmpty()
            val email = backStackEntry.arguments?.getString("email").orEmpty()
            val pass = backStackEntry.arguments?.getString("pass").orEmpty()
            val cursos = backStackEntry.arguments?.getString("cursos").orEmpty()
            PantallaApuntes(navController, nombre, apellido, email, pass, cursos)
        }

        composable("verApunte") { PantallaApuntesDummy(navController) }
        composable("editarApunte") { PantallaEditarApunteDummy(navController) }
        composable("crearApunte") { PantallaCrearApunte(navController) }

        composable("misCursosProfesor") { MisCursosProfesor(navController) }
        composable("vistaCursoProfesor") { VistaCursoProfesor(navController) }
        composable("asignarTarea") { PantallaAsignarTarea(navController) }
        composable("revisarTareas") { PantallaRevisarTareas(navController) }
        composable("asignarVocabulario") { PantallaAsignarVocabulario(navController) }
        composable("vocabulariosProfesor") { PantallaVocabularios(navController) }
    }
}