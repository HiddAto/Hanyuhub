package com.example.hanyuhub.repository

import android.util.Log
import com.example.hanyuhub.model.LoginDto
import com.example.hanyuhub.model.UsuarioDto
import com.example.hanyuhub.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UsuarioRepository {

    private val api = RetrofitClient.apiService

    suspend fun registrarUsuario(usuario: UsuarioDto): Response<Map<String, Any>> {
        return api.registrarUsuario(usuario)
    }
    suspend fun login(email: String, pass: String): UsuarioDto? {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitClient.apiService.login(LoginDto(email, pass))
                if (response.isSuccessful) {
                    response.body() // UsuarioDto
                } else {
                    Log.e("UsuarioService", "Error en login: ${response.code()} ${response.errorBody()?.string()}")
                    null
                }
            } catch (e: Exception) {
                Log.e("UsuarioService", "Excepción login", e)
                null
            }
        }
    }

    suspend fun validarEmail(email: String): Boolean {
        val response = api.validarEmail(email)
        if (response.isSuccessful) {
            val body = response.body() // body es Map<String, Boolean>?
            return body?.get("existe") ?: false
        } else {
            throw Exception("Error al validar email: ${response.message()}")
        }
    }
}