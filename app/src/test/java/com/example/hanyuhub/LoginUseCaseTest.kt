package com.example.hanyuhub

import com.example.hanyuhub.domain.LoginUseCase
import com.example.hanyuhub.model.UsuarioDto
import com.example.hanyuhub.repository.UsuarioRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class LoginUseCaseTest {
    private val repository = mockk<UsuarioRepository>()
    private val useCase = LoginUseCase(repository)

    // Caso: email vacío, debe devolver false
    @Test
    fun email_vacio() = runTest {
        val resultado = useCase.login("", "1234", "estudiante")
        assertFalse(resultado)
    }

    // Caso: password vacío, debe devolver false
    @Test
    fun pass_vacio() = runTest {
        val resultado = useCase.login("mail@test.com", "", "estudiante")
        assertFalse(resultado)
    }

    // Caso: login válido con rol estudiante
    @Test
    fun login_estudiante_ok() = runTest {
        val usuario = UsuarioDto(
            mail = "alumno@test.com",
            pass = "1234",
            nombre = "Ana",
            apellido = "López",
            rol = "estudiante"
        )

        coEvery { repository.login("alumno@test.com", "1234") } returns usuario

        val resultado = useCase.login("alumno@test.com", "1234", "estudiante")
        assertTrue(resultado)
    }

    // Caso: login falla si el rol no coincide (profesor intentando entrar como estudiante)
    @Test
    fun rol_incorrecto() = runTest {
        val usuario = UsuarioDto(
            mail = "prof@test.com",
            pass = "1234",
            nombre = "Carlos",
            apellido = "Ramírez",
            rol = "profesor"
        )

        coEvery { repository.login("prof@test.com", "1234") } returns usuario

        val resultado = useCase.login("prof@test.com", "1234", "estudiante")
        assertFalse(resultado)
    }

    // Caso: login falla cuando el repositorio devuelve null (usuario no encontrado)
    @Test
    fun usuario_null() = runTest {
        coEvery { repository.login("x@test.com", "0000") } returns null

        val resultado = useCase.login("x@test.com", "0000", "profesor")
        assertFalse(resultado)
    }
}