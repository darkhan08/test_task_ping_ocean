package com.example.testtaskpingocean.data.repository

import com.example.testtaskpingocean.data.UserPreferences
import com.example.testtaskpingocean.data.entity.LoginResponse
import com.example.testtaskpingocean.data.entity.UserProfile
import com.example.testtaskpingocean.di.provideAuthApiService
import com.example.testtaskpingocean.di.provideAuthOkhttpClient
import com.example.testtaskpingocean.di.provideProfileApiService
import com.example.testtaskpingocean.di.provideProfileOkhttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.util.*

class LoginRepository(
    private val userPreferences: UserPreferences
) {

    suspend fun doLogin(email: String, password: String): Response<LoginResponse> {
        val parm = hashMapOf<String, String>()
        parm["email"] = email.toLowerCase(Locale.ROOT)
        parm["password"] = password

        return withContext(Dispatchers.IO) {
            provideAuthApiService(provideAuthOkhttpClient()).doLogin(parm)
        }
    }

    suspend fun saveToke(token: String) {
        withContext(Dispatchers.IO) {
            userPreferences.token = token
        }
    }

    fun getToke() = userPreferences.token

    suspend fun getProfile(): Response<UserProfile> {
        return withContext(Dispatchers.IO) {
            provideProfileApiService(provideProfileOkhttpClient(userPreferences)).getProfile()
        }
    }
}