package com.example.hanyuhub.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hanyuhub.model.Coleccion
import com.example.hanyuhub.model.Palabra
import com.example.hanyuhub.repository.ColeccionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ColeccionViewModel : ViewModel() {

    private val repo = ColeccionRepository()

    private val _colecciones = MutableStateFlow<List<Coleccion>>(emptyList())
    val colecciones: StateFlow<List<Coleccion>> get() = _colecciones

    private val _palabras = MutableStateFlow<List<Palabra>>(emptyList())
    val palabras: StateFlow<List<Palabra>> get() = _palabras

    fun cargarColecciones(mail: String) {
        viewModelScope.launch {
            try {
                _colecciones.value = repo.listarColecciones(mail)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun crearColeccion(nombre: String, mail: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                repo.crearColeccion(nombre, mail)
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun CoroutineScope.onSuccess() {
        TODO("Not yet implemented")
    }

    fun eliminarColeccion(id: Long, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                repo.eliminarColeccion(id)
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun cargarPalabras(idColeccion: Long) {
        viewModelScope.launch {
            try {
                _palabras.value = repo.listarPalabras(idColeccion)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun agregarPalabra(idColeccion: Long, palabra: Palabra, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                repo.agregarPalabra(idColeccion, palabra)
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
