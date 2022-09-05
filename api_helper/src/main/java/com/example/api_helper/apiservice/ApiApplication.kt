package com.example.api_helper.apiservice

import android.app.Application
import android.content.Context

class ApiApplication: Application() {
    init {
        instance = this
    }
    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        var instance: ApiApplication? = null

        fun getApllicationContext(): Context =
            instance!!.applicationContext
    }
}