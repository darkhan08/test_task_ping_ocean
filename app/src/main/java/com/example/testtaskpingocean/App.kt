package com.example.testtaskpingocean

import android.app.Application
import com.example.testtaskpingocean.di.appModule
import com.example.testtaskpingocean.di.loginRepositoryModule
import com.example.testtaskpingocean.di.networkModule
import com.example.testtaskpingocean.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, networkModule, loginRepositoryModule, viewModule))
        }
    }
}