package com.example.testtaskpingocean.di

import com.example.testtaskpingocean.BuildConfig
import com.example.testtaskpingocean.data.UserPreferences
import com.example.testtaskpingocean.data.api.AuthApiService
import com.example.testtaskpingocean.data.api.ProfileApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URI = "https://dev-api.ringapp.me/"

val networkModule = module {
    single(named("auth")) { provideAuthOkhttpClient() }
    single { provideAuthApiService(get(named("auth"))) }

    single(named("profile")) { provideProfileOkhttpClient(get()) }
    single { provideProfileApiService(get(named("profile"))) }
}

fun provideAuthOkhttpClient(): OkHttpClient {
    val okHttpClient = OkHttpClient.Builder()
        .also { client ->
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }
        }.build()
    return okHttpClient
}

fun provideAuthApiService(client: OkHttpClient): AuthApiService {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(BASE_URI)
        .build()

    return retrofit.create(AuthApiService::class.java)
}

fun provideProfileOkhttpClient(userPreferences: UserPreferences? = null): OkHttpClient {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            chain.proceed(chain.request().newBuilder().also {
                it.addHeader("Authorization", "Bearer ${userPreferences?.token.orEmpty()}")
            }.build())
        }
        .also { client ->
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }
        }.build()
    return okHttpClient
}

fun provideProfileApiService(client: OkHttpClient): ProfileApiService {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(BASE_URI)
        .build()
    return retrofit.create(ProfileApiService::class.java)
}

