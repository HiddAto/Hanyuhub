package com.example.hanyuhub.domain

import com.example.hanyuhub.repository.UsuarioRepository

class LoginUseCase(private val repository: UsuarioRepository) {

    suspend fun login(email: String, pass: String, rolEsperado: String): Boolean {
        // Correo o contraseña están vacíos return false
        if (email.isBlank() || pass.isBlank()) return false

        // Se llama al repositorio para obtener el usuario con el email y contraseña
        val usuario = repository.login(email, pass)

        // Se retorna true solo si se encontró un usuario válido y el rol del usuario coincide con el rol esperado
        return usuario != null &&
                usuario.rol.equals(rolEsperado, ignoreCase = true)
    }
}