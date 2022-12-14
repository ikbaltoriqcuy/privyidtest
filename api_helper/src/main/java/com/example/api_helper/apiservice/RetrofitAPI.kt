package com.example.api_helper.apiservice

import android.content.Context
import android.os.Build
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitAPI {
    const val baseURL = "http://pretest-qa.dcidev.id/api/"

    fun getRetrofitApi(context: Context): ServiceApi {
        val okHttpChannel = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .cache(null).apply {
                if (Build.VERSION.SDK_INT < 31)
                    addInterceptor(ChuckInterceptor(context))
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpChannel)
            .build()
        return retrofit.create(ServiceApi::class.java)
    }

}