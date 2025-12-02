package com.example.hanyuhub.ui.vocabulario

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hanyuhub.viewmodel.ColeccionViewModel

@Composable
fun PantallaDetalleColeccion(
    navController: NavController,
    idColeccion: Long,
    mail: String,
    viewModel: ColeccionViewModel = viewModel()
) {
    val palabras by viewModel.palabras.collectAsState()

    LaunchedEffect(idColeccion) {
        viewModel.cargarPalabras(idColeccion)
    }

    Column {
        Text("Palabras de la colección $idColeccion")

        LazyColumn {
            items(palabras) { palabra ->
                Text("${palabra.hanzi} - ${palabra.significado}", Modifier.padding(10.dp))
            }
        }
    }
}
