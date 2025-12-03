package com.example.hanyuhub.repository

import android.util.Log
import com.example.hanyuhub.model.Tareas
import com.example.hanyuhub.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class TareaRepository {

    private val api = RetrofitClient.apiService

    // Crear tarea
    suspend fun crearTarea(tarea: Tareas): Tareas? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.crearTarea(tarea)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    Log.e("TareaRepository", "Error crear tarea: ${response.code()} ${response.errorBody()?.string()}")
                    null
                }
            } catch (e: Exception) {
                Log.e("TareaRepository", "Excepción crear tarea", e)
                null
            }
        }
    }

    // Editar tarea
    suspend fun editarTarea(id: String, tarea: Tareas): Tareas? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.editarTarea(id, tarea)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    Log.e("TareaRepository", "Error editar tarea: ${response.code()} ${response.errorBody()?.string()}")
                    null
                }
            } catch (e: Exception) {
                Log.e("TareaRepository", "Excepción editar tarea", e)
                null
            }
        }
    }

    // Borrar tarea
    suspend fun borrarTarea(id: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.borrarTarea(id)
                response.isSuccessful
            } catch (e: Exception) {
                Log.e("TareaRepository", "Excepción borrar tarea", e)
                false
            }
        }
    }

    // Listar todas las tareas
    suspend fun listarTareas(): List<Tareas>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.listarTareas()
                if (response.isSuccessful) response.body() else null
            } catch (e: Exception) {
                Log.e("TareaRepository", "Error listar tareas", e)
                null
            }
        }
    }

    // Obtener tarea por ID
    suspend fun obtenerTarea(id: String): Tareas? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.obtenerTarea(id)
                if (response.isSuccessful) response.body() else null
            } catch (e: Exception) {
                Log.e("TareaRepository", "Error obtener tarea", e)
                null
            }
        }
    }

    // Listar tareas por curso
    suspend fun listarTareasPorCurso(curso: String): List<Tareas>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.listarTareasPorCurso(curso)
                if (response.isSuccessful) response.body() else null
            } catch (e: Exception) {
                Log.e("TareaRepository", "Error listar tareas por curso", e)
                null
            }
        }
    }
}

