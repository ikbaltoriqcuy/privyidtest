package com.example.api_helper.apiservice

import android.content.Context
import org.json.JSONObject
import retrofit2.Call

class RepositoryAPI(val context: Context) {

    fun login(phone: String,
              password: String,
              latlong: String,
              deviceToken: String,
              deviceType: Int): Call<JSONObject> =
        RetrofitAPI.getRetrofitApi(context).signIn(phone, password, latlong, deviceToken, deviceType)
}