package com.example.testtaskpingocean.data.api

import com.example.testtaskpingocean.data.entity.UserProfile
import retrofit2.Response
import retrofit2.http.GET

interface ProfileApiService {
    @GET("profile")
    suspend fun getProfile(): Response<UserProfile>
}