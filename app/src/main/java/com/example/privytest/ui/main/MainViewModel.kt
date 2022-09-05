package com.example.privytest.ui.main

import com.example.api_helper.apiservice.RepositoryAPI
import com.example.common_base.BaseViewModel
import com.example.util.Hawkutil

class MainViewModel(val repository: RepositoryAPI): BaseViewModel() {

    init { checkRoute() }

    fun checkRoute() {
        if (Hawkutil.getPhone().isNullOrEmpty())
            route.value = ROUTE_LOGIN
    }

    companion object {
        const val ROUTE_LOGIN = "login"
        const val ROUTE_OTP = "otp"
    }
}