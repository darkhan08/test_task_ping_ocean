package com.example.testtaskpingocean.di

import com.example.testtaskpingocean.ui.login.LoginViewModel
import com.example.testtaskpingocean.ui.profile.ProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        LoginViewModel(get())
    }

    viewModel {
        ProfileViewModel(get())
    }
}