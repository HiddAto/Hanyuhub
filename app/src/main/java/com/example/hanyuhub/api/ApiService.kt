package com.example.hanyuhub.api

import com.example.hanyuhub.model.Coleccion
import com.example.hanyuhub.model.CrearColeccionDTO
import com.example.hanyuhub.model.LoginDto
import com.example.hanyuhub.model.Palabra
import com.example.hanyuhub.model.Tareas
import com.example.hanyuhub.model.UsuarioDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
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

    // Obtener usuario por mail
    @GET("usuario/{mail}")
    suspend fun obtenerUsuarioPorMail(@Path("mail") mail: String): Response<UsuarioDto>

    // Obtener colecciones de un usuario
    @GET("api/colecciones/{mail}")
    fun listarColecciones(
        @Path("mail") mail: String
    ): Call<List<Coleccion>>

    // Crear colección
    @POST("api/colecciones")
    suspend fun crearColeccion(@Body dto: CrearColeccionDTO): Response<Unit>

    // Eliminar colección
    @DELETE("api/colecciones/{id}")
    fun eliminarColeccion(
        @Path("id") id: Long
    ): Call<Void>


    // Obtener palabras de una colección
    @GET("api/palabras/coleccion/{idColeccion}")
    fun listarPalabras(
        @Path("idColeccion") idColeccion: Long
    ): Call<List<Palabra>>

    // Agregar palabra a una colección
    @POST("api/palabras/{idColeccion}")
    fun agregarPalabra(
        @Path("idColeccion") idColeccion: Long,
        @Body palabra: Palabra
    ): Call<Palabra>


    // Crear tarea (solo profesores en backend)
    @POST("tareas/crear")
    suspend fun crearTarea(@Body tarea: Tareas): Response<Tareas>

    // Editar tarea (solo profesores en backend)
    @PUT("tareas/editar/{id}")
    suspend fun editarTarea(
        @Path("id") id: String,
        @Body tarea: Tareas
    ): Response<Tareas>

    // Borrar tarea (solo profesores en backend)
    @DELETE("tareas/borrar/{id}")
    suspend fun borrarTarea(@Path("id") id: String): Response<Void>

    // Listar todas las tareas (alumnos y profesores)
    @GET("tareas")
    suspend fun listarTareas(): Response<List<Tareas>>

    // Obtener tarea por id
    @GET("tareas/{id}")
    suspend fun obtenerTarea(@Path("id") id: String): Response<Tareas>

    // Listar tareas por curso
    @GET("tareas/curso/{curso}")
    suspend fun listarTareasPorCurso(@Path("curso") curso: String): Response<List<Tareas>>
}