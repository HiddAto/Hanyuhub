package com.example.hanyuhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hanyuhub.ui.home.PantallaHome
import com.example.hanyuhub.ui.theme.HanyuHubTheme

import com.example.hanyuhub.ui.login.PantallaLogin
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
        startDestination = "login") {
        composable ("login") { PantallaLogin(navController) }
        composable(
            "home/{usuario}",
            arguments = listOf(navArgument("usuario"){
                type = NavType.StringType
            })
            ) { backStackEntry ->
            val usuario = backStackEntry.arguments?.getString("usuario").orEmpty()
            PantallaHome(navController, usuario)
        }
        composable("start") { PantallaInicio(navController) }
    }
}