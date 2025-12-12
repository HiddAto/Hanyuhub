package com.example.hanyuhub.domain

import com.example.hanyuhub.model.UsuarioDto
import com.example.hanyuhub.repository.UsuarioRepository

class RegistroUseCase(private val repository: UsuarioRepository) {

    suspend fun registrar(usuario: UsuarioDto): Boolean {
        // Se validan campos vacíos
        if (usuario.mail.isBlank() || usuario.pass.isBlank() ||
            usuario.nombre.isBlank() || usuario.apellido.isBlank()
        ) return false

        // Se valida si el email ya existe
        val emailExiste = repository.validarEmail(usuario.mail)
        if (emailExiste) return false

        // Se registra usuario
        return repository.registrarUsuario(usuario).isSuccessful
    }
}