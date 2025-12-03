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
import com.example.hanyuhub.ui.ejercicios.PantallaEjercicios
import com.example.hanyuhub.ui.foro.PantallaCrearPost
import com.example.hanyuhub.ui.foro.PantallaForo
import com.example.hanyuhub.ui.perfil.PantallaPerfilAlumno
import com.example.hanyuhub.ui.perfil.PantallaPerfilProfesor
import com.example.hanyuhub.ui.profesor.CrearVocabularioProfe
import com.example.hanyuhub.ui.profesor.PantallaAsignarTarea
import com.example.hanyuhub.ui.profesor.PantallaAsignarVocabulario
import com.example.hanyuhub.ui.profesor.PantallaRevisarTareas
import com.example.hanyuhub.ui.profesor.PantallaVocabularios
import com.example.hanyuhub.ui.profesor.VistaCursoProfesor
import com.example.hanyuhub.ui.qr.PerfilAlumnoQr
import com.example.hanyuhub.ui.qr.QrAlumnoScreen
import com.example.hanyuhub.ui.qr.QrProfesorScreen
import com.example.hanyuhub.ui.register.PantallaRegistro
import com.example.hanyuhub.ui.start.PantallaInicio
import com.example.hanyuhub.ui.tarea.PantallaRevisarTarea
import com.example.hanyuhub.ui.tarea.PantallaTareas
import com.example.hanyuhub.ui.vocabulario.PantallaColecciones
import com.example.hanyuhub.ui.vocabulario.PantallaCrearColeccion
import com.example.hanyuhub.ui.vocabulario.PantallaCrearVocabulario
import com.example.hanyuhub.ui.vocabulario.PantallaDetalleColeccion
import com.example.hanyuhub.ui.vocabulario.PantallaVocabulario
import com.example.hanyuhub.ui.vocabulario.PantallaVocabularioApp
import com.example.hanyuhub.ui.vocabulario.PantallaVocabularioPers

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
            "apuntes/{nombre}/{apellido}/{email}/{pass}/{curso}",
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
            PantallaApuntes(navController, nombre, apellido, email, pass, curso)
        }

        composable("verApunte") { PantallaApuntesDummy(navController) }
        composable("editarApunte") { PantallaEditarApunteDummy(navController) }
        composable("crearApunte") { PantallaCrearApunte(navController) }

        composable(
            "tareasAlumno/{nombre}/{apellido}/{email}/{pass}/{curso}",
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
            PantallaTareas(navController, nombre, apellido, email, pass, curso)
        }

        composable("verTarea") { PantallaRevisarTarea(navController) }

        composable(
            "ejercicios/{nombre}/{apellido}/{email}/{pass}/{curso}",
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
            PantallaEjercicios(navController, nombre, apellido, email, pass, curso)
        }

        composable(
            "vocabularios/{nombre}/{apellido}/{email}/{pass}/{curso}",
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
            PantallaVocabulario(navController, nombre, apellido, email, pass, curso)
        }

        composable("vocabularioApp") { PantallaVocabularioApp(navController) }
        composable("vocabularioPers") { PantallaVocabularioPers(navController) }
        composable("crearVocabulario") { PantallaCrearVocabulario(navController) }

        composable(
            "foro/{nombre}/{apellido}/{email}/{pass}/{curso}",
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
            PantallaForo(navController, nombre, apellido, email, pass, curso)
        }

        composable("crearPost") { PantallaCrearPost(navController) }

        composable("misCursosProfesor") { MisCursosProfesor(navController) }
        composable("vistaCursoProfesor") { VistaCursoProfesor(navController) }
        composable("revisarTareas") { PantallaRevisarTareas(navController) }
        composable("asignarVocabulario") { PantallaAsignarVocabulario(navController) }
        composable("vocabulariosProfesor") { PantallaVocabularios(navController) }
        composable("crearVocabularioProfe") { CrearVocabularioProfe(navController) }

        composable(
            route = "misColecciones/{mail}/{nombre}/{apellido}/{email}/{pass}/{curso}",
            arguments = listOf(
                navArgument("mail") { type = NavType.StringType },
                navArgument("nombre") { type = NavType.StringType },
                navArgument("apellido") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType },
                navArgument("pass") { type = NavType.StringType },
                navArgument("curso") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val mail = backStackEntry.arguments?.getString("mail") ?: ""
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val apellido = backStackEntry.arguments?.getString("apellido") ?: ""
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val pass = backStackEntry.arguments?.getString("pass") ?: ""
            val curso = backStackEntry.arguments?.getString("curso") ?: ""

            PantallaColecciones(
                navController = navController,
                mail = mail,
                nombre = nombre,
                apellido = apellido,
                email = email,
                pass = pass,
                curso = curso
            )
        }

        composable(
            "detalleColeccion/{idColeccion}/{mail}",
            arguments = listOf(
                navArgument("idColeccion"){ type = NavType.StringType },
                navArgument("mail"){ type = NavType.StringType }
            )
        ) { backStackEntry ->
            val idColeccion = backStackEntry.arguments?.getString("idColeccion")!!.toLong()
            val mail = backStackEntry.arguments?.getString("mail").orEmpty()
            PantallaDetalleColeccion(navController, idColeccion, mail)
        }

        composable(
            "crearColeccion/{nombre}/{apellido}/{email}/{pass}/{curso}/{mail}",
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("apellido") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType },
                navArgument("pass") { type = NavType.StringType },
                navArgument("curso") { type = NavType.StringType },
                navArgument("mail") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val nombre = backStackEntry.arguments?.getString("nombre").orEmpty()
            val apellido = backStackEntry.arguments?.getString("apellido").orEmpty()
            val email = backStackEntry.arguments?.getString("email").orEmpty()
            val pass = backStackEntry.arguments?.getString("pass").orEmpty()
            val curso = backStackEntry.arguments?.getString("curso").orEmpty()
            val mail = backStackEntry.arguments?.getString("mail").orEmpty()

            PantallaCrearColeccion(
                navController = navController,
                mail = mail,        // <-- mail que va al backend
                nombre = nombre,
                apellido = apellido,
                email = email,
                pass = pass,
                curso = curso
            )
        }

        composable(
            "qrAlumno/{nombre}/{apellido}/{email}/{pass}/{curso}",
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("apellido") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType },
                navArgument("pass") { type = NavType.StringType },
                navArgument("curso") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val nombre = backStackEntry.arguments?.getString("nombre").orEmpty()
            val apellido = backStackEntry.arguments?.getString("apellido").orEmpty()
            val email = backStackEntry.arguments?.getString("email").orEmpty()
            val pass = backStackEntry.arguments?.getString("pass").orEmpty()
            val curso = backStackEntry.arguments?.getString("curso").orEmpty()

            QrAlumnoScreen(
                navController = navController,
                nombre = nombre,
                apellido = apellido,
                email = email,
                pass = pass,
                curso = curso
            )
        }

        composable(
            "qrProfesor/{nombre}/{apellido}/{email}/{pass}/{cursos}",
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("apellido") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType },
                navArgument("pass") { type = NavType.StringType },
                navArgument("cursos") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre").orEmpty()
            val apellido = backStackEntry.arguments?.getString("apellido").orEmpty()
            val email = backStackEntry.arguments?.getString("email").orEmpty()
            val pass = backStackEntry.arguments?.getString("pass").orEmpty()
            val cursos = backStackEntry.arguments?.getString("cursos").orEmpty()

            QrProfesorScreen(
                navController = navController,
                nombre = nombre,
                apellido = apellido,
                email = email,
                pass = pass,
                cursos = cursos
            )
        }

        composable(
            "perfilAlumnoQr/{aNombre}/{aApellido}/{aEmail}/{aPass}/{aCurso}/{pNombre}/{pApellido}/{pEmail}/{pPass}/{pCursos}",
            arguments = listOf(
                navArgument("aNombre"){ type = NavType.StringType },
                navArgument("aApellido"){ type = NavType.StringType },
                navArgument("aEmail"){ type = NavType.StringType },
                navArgument("aPass"){ type = NavType.StringType },
                navArgument("aCurso"){ type = NavType.StringType },
                navArgument("pNombre"){ type = NavType.StringType },
                navArgument("pApellido"){ type = NavType.StringType },
                navArgument("pEmail"){ type = NavType.StringType },
                navArgument("pPass"){ type = NavType.StringType },
                navArgument("pCursos"){ type = NavType.StringType }
            )
        ) { backStackEntry ->
            val aNombre = backStackEntry.arguments?.getString("aNombre").orEmpty()
            val aApellido = backStackEntry.arguments?.getString("aApellido").orEmpty()
            val aEmail = backStackEntry.arguments?.getString("aEmail").orEmpty()
            val aPass = backStackEntry.arguments?.getString("aPass").orEmpty()
            val aCurso = backStackEntry.arguments?.getString("aCurso").orEmpty()

            val pNombre = backStackEntry.arguments?.getString("pNombre").orEmpty()
            val pApellido = backStackEntry.arguments?.getString("pApellido").orEmpty()
            val pEmail = backStackEntry.arguments?.getString("pEmail").orEmpty()
            val pPass = backStackEntry.arguments?.getString("pPass").orEmpty()
            val pCursos = backStackEntry.arguments?.getString("pCursos").orEmpty()

            PerfilAlumnoQr(
                navController = navController,
                nombre = aNombre,
                apellido = aApellido,
                email = aEmail,
                pass = aPass,
                curso = aCurso,
                profNombre = pNombre,
                profApellido = pApellido,
                profEmail = pEmail,
                profPass = pPass,
                profCursos = pCursos
            )
        }

        composable(
            "tareasProfesor/{nombre}/{apellido}/{email}/{pass}/{curso}",
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
            PantallaAsignarTarea(navController, nombre, apellido, email, pass, curso)
        }

    }
}