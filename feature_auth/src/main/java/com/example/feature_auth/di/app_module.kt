package com.example.feature_auth.di

import com.example.feature_auth.ui.login.LoginViewModel
import com.example.feature_auth.ui.otp.OTPViewModel
import com.example.feature_auth.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { OTPViewModel(get()) }
}