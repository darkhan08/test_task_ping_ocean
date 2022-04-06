package com.example.testtaskpingocean.di

import com.example.testtaskpingocean.data.repository.LoginRepository
import org.koin.dsl.module

val loginRepositoryModule = module{
    single { LoginRepository(get()) }
}