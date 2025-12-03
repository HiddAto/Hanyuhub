package com.example.hanyuhub.repository

import com.example.hanyuhub.model.Coleccion
import com.example.hanyuhub.model.CrearColeccionDTO
import com.example.hanyuhub.model.Palabra
import com.example.hanyuhub.network.RetrofitClient
import retrofit2.await
import retrofit2.awaitResponse

class ColeccionRepository {

    private val api = RetrofitClient.apiService

    suspend fun listarColecciones(mail: String): List<Coleccion> {
        return api.listarColecciones(mail).await()
    }

    suspend fun crearColeccion(nombre: String, mail: String) {
        val dto = CrearColeccionDTO(nombre, mail)

        val response = api.crearColeccion(dto)

        if (!response.isSuccessful) {
            throw Exception("Error al crear la colección")
        }
    }
    suspend fun eliminarColeccion(id: Long) {
        api.eliminarColeccion(id).awaitResponse()
    }

    suspend fun listarPalabras(idColeccion: Long): List<Palabra> {
        return api.listarPalabras(idColeccion).await()
    }

    suspend fun agregarPalabra(idColeccion: Long, palabra: Palabra): Palabra {
        return api.agregarPalabra(idColeccion, palabra).await()
    }
}
