package com.example.hanyuhub

import com.example.hanyuhub.domain.RegistroUseCase
import com.example.hanyuhub.model.UsuarioDto
import com.example.hanyuhub.repository.UsuarioRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Test
import retrofit2.Response

class RegistroUseCaseTest {

    private val repository = mockk<UsuarioRepository>()
    private val useCase = RegistroUseCase(repository)

    private val usuario = UsuarioDto(
        mail = "nuevo@mail.com",
        pass = "1234",
        nombre = "Ana",
        apellido = "Lopez",
        rol = "estudiante"
    )

    // Caso: registro exitoso, devuelve true
    @Test
    fun registro_exitoso() = runTest {
        coEvery { repository.validarEmail(usuario.mail) } returns false
        coEvery { repository.registrarUsuario(usuario) } returns Response.success(mapOf("ok" to true))

        val resultado = useCase.registrar(usuario)
        assertTrue(resultado)
    }

    // Caso: registro falla si email ya existe, devuelve false
    @Test
    fun email_existente() = runTest {
        coEvery { repository.validarEmail(usuario.mail) } returns true

        val resultado = useCase.registrar(usuario)
        assertFalse(resultado)
    }

    // Caso: registro falla si la API devuelve error, devuelve false
    @Test
    fun api_error() = runTest {
        coEvery { repository.validarEmail(usuario.mail) } returns false
        coEvery { repository.registrarUsuario(usuario) } returns Response.error(
            400,
            ResponseBody.create(null, "error")
        )

        val resultado = useCase.registrar(usuario)
        assertFalse(resultado)
    }

    // Caso: registro falla si faltan datos, devuelve false
    @Test
    fun datos_incompletos() = runTest {
        val usuarioInvalido = UsuarioDto("", "", "", "", "estudiante")

        val resultado = useCase.registrar(usuarioInvalido)
        assertFalse(resultado)
    }
}