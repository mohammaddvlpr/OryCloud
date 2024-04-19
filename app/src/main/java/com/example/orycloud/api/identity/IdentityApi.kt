package com.example.orycloud.api.identity

import com.example.orycloud.repo.identity.LoginFlowRepoModel
import com.example.orycloud.repo.identity.RegistrationSchemaRepoModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface IdentityApi {

    @GET("self-service/registration/api")
    suspend fun getRegistrationFlow(
        @Header("Authorization") apiKey: String
    ): Response<RegistrationSchemaRepoModel>


    @POST("self-service/registration")
    suspend fun register(
        @Header("Authorization") apiKey: String, @Query("flow") flowId: String,
        @Body hashFields: Map<String, String>
    ): Response<Any>


    @GET("self-service/login/api")
    suspend fun getLoginFlow(
        @Header("Authorization") apiKey: String
    ): Response<LoginFlowRepoModel>

    @POST("self-service/login")
    suspend fun login(
        @Header("Authorization") apiKey: String, @Query("flow") flowId: String,
        @Body hashFields: Map<String, String>
    ): Response<Any>
}