package com.example.hanyuhub.api

import com.example.hanyuhub.model.LoginDto
import com.example.hanyuhub.model.UsuarioDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    //@Headers("Content-Type: application/json")
    @POST("/usuario/login")
    suspend fun login(@Body loginDto: LoginDto): Response<UsuarioDto>

    // Registro de usuario
    @POST("usuario/registro")
    suspend fun registrarUsuario(@Body usuario: UsuarioDto): Response<Map<String, Any>>

    // Validación de email al momento de registrar un usuario
    @GET("usuario/validar-email")
    suspend fun validarEmail(@Query("mail") email: String): Response<Map<String, Boolean>>
}