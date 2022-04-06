package com.example.testtaskpingocean.di

import com.example.testtaskpingocean.data.UserPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { UserPreferences(androidContext()) }
}