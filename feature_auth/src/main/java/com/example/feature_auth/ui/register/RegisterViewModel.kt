package com.example.feature_auth.ui.register

import android.os.Build
import android.util.Log
import com.example.api_helper.apiservice.RepositoryAPI
import com.example.common_base.BaseViewModel
import com.example.feature_auth.model.BaseResponse
import com.example.feature_auth.model.User
import com.example.util.Hawkutil
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(val repository: RepositoryAPI): BaseViewModel() {

    fun register(phone: String, password: String, country: String) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                isLoading.value = true
                repository.register(
                        phone,
                        password,
                        country,
                    phone + Build.DEVICE + Build.ID
                ).enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        if (response.isSuccessful) {
                            isLoading.value = false
                            if (response.code() == 201) {
                                response.body()?.let {
                                    route.value = ROUTE_OTP

                                    val user = Gson().fromJson(
                                        it.getAsJsonObject("data").getAsJsonObject("user").toString(),
                                        User::class.java
                                    )
                                    Hawkutil.run {
                                        setUserID(user.id)
                                        setPhone(user.phone)
                                        setToken(user.userDevice.deviceToken)
                                    }
                                }
                            }
                            else route.value = ROUTE_FAILED
                        } else {
                            route.value = ROUTE_FAILED
                        }
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        isLoading.value = false
                        route.value = ROUTE_FAILED
                    }
                })

            }
        }
    }

    companion object {
        const val ROUTE_OTP = "main"
        const val ROUTE_FAILED = "failed"
    }
}