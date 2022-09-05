package com.example.privytest.di

import com.example.privytest.ui.fragment.ProfileViewModel
import com.example.privytest.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}