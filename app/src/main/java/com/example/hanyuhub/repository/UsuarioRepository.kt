package com.example.hanyuhub.repository

import android.util.Log
import com.example.hanyuhub.model.LoginDto
import com.example.hanyuhub.model.UsuarioDto
import com.example.hanyuhub.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsuarioRepository {

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
}