package com.example.testtaskpingocean.data.api

import com.example.testtaskpingocean.data.entity.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth")
    suspend fun doLogin(@Body params: HashMap<String, String>): Response<LoginResponse>

}