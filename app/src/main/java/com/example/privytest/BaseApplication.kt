package com.example.privytest

import android.app.Application
import com.example.api_helper.apiservice.RepositoryAPI
import com.example.feature_auth.di.authModule
import com.example.privytest.di.mainModule
import com.example.util.Hawkutil
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Hawkutil.build(applicationContext)
        setupCoin()
    }

    private fun setupCoin() {
        val repositoryModule = module {
            single { RepositoryAPI(applicationContext) }
        }
        val appModule = mutableListOf(
            mainModule,
            authModule,
            repositoryModule
        )
        startKoin {
            modules(appModule)
        }.koin
    }
}