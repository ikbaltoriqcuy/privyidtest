package com.example.api_helper.apiservice

import android.content.Context
import com.google.gson.JsonObject
import retrofit2.Call

class RepositoryAPI(val context: Context) {

    fun login(phone: String,
              password: String,
              deviceToken: String): Call<JsonObject> =
        RetrofitAPI.getRetrofitApi(context).signIn(
            phone,
            password,
            LATLONG,
            deviceToken,
            DEVICE_TYPE
        )

    fun register(phone: String,
                 password: String,
                 country: String,
                 deviceToken: String): Call<JsonObject> =
        RetrofitAPI.getRetrofitApi(context).register(
            phone,
            password,
            country,
            LATLONG,
            deviceToken,
            DEVICE_TYPE
        )

    fun resendOtp(phone: String): Call<JsonObject> =
        RetrofitAPI.getRetrofitApi(context).resendOTP(
            phone
        )


    fun matchOtp(userId: String, otpCode: String): Call<JsonObject> =
        RetrofitAPI.getRetrofitApi(context).checkOTP(
            userId , otpCode
        )


    companion object {
        const val INDOSIAN_COUNTRY = "Indonesia"
        const val LATLONG = "0"
        const val DEVICE_TYPE = 1
    }
}