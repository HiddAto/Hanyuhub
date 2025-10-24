package com.example.hanyuhub.api

import com.example.hanyuhub.model.LoginDto
import com.example.hanyuhub.model.UsuarioDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    //@Headers("Content-Type: application/json")
    @POST("/usuario/login")
    suspend fun login(
        @Body loginDto: LoginDto
    ): Response<UsuarioDto>
}