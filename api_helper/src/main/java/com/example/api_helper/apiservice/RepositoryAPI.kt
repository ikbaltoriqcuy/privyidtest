package com.example.api_helper.apiservice

import android.content.Context
import com.example.util.Hawkutil
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

    fun saveProfile(name: String,
                    gender: String,
                    bithday: String,
                    hometown: String,
                    bio: String): Call<JsonObject> =
        RetrofitAPI.getRetrofitApi(context).updateProfile(
            Hawkutil.getToken(),
            name ,
            1,
            bithday,
            hometown,
            bio
        )

    fun saveEducation(schoolName: String,
                    graduationTime: String): Call<JsonObject> =
        RetrofitAPI.getRetrofitApi(context).updateEducation(
            Hawkutil.getToken(),
            schoolName,
            graduationTime
        )


    fun saveCareer(position: String,
                   companyName: String,
                   start: String,
                   end: String): Call<JsonObject> =
        RetrofitAPI.getRetrofitApi(context).updateCareer(
            Hawkutil.getToken(),
            position,
            companyName,
            start,
            end
        )


    fun getProfile(): Call<JsonObject> =
        RetrofitAPI.getRetrofitApi(context).getProfile(
            Hawkutil.getToken()
        )


    fun logout(): Call<JsonObject> =
        RetrofitAPI.getRetrofitApi(context).signOut(
            Hawkutil.getToken(),
            CONFIRM_SIGNOUT
        )


    fun getMessage(userId: String): Call<JsonObject> =
        RetrofitAPI.getRetrofitApi(context).getMessage(
            Hawkutil.getToken(),
            userId
        )

    fun sendMessage(userId: String, message: String): Call<JsonObject> =
        RetrofitAPI.getRetrofitApi(context).sendMessage(
            Hawkutil.getToken(),
            userId,
            message
        )

    companion object {
        const val INDOSIAN_COUNTRY = "Indonesia"
        const val LATLONG = "0"
        const val DEVICE_TYPE = 1
        const val CONFIRM_SIGNOUT = 1
    }
}