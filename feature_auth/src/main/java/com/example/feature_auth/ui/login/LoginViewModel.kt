package com.example.feature_auth.ui.login

import android.os.Build
import com.example.api_helper.apiservice.RepositoryAPI
import com.example.common_base.BaseViewModel
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

class LoginViewModel(val repository: RepositoryAPI): BaseViewModel() {

    fun login(phone: String, password: String) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                isLoading.value = true
                repository.login(
                        phone,
                        password,
                        phone + Build.DEVICE + Build.ID
                ).enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        isLoading.value = false
                        if (response.isSuccessful) {
                            route.value = ROUTE_MAIN
                            if (response.code() == 201)
                            response.body()?.let {
                                val user = Gson().fromJson(
                                    it.getAsJsonObject("data").getAsJsonObject("user").toString(),
                                    User::class.java
                                )
                                Hawkutil.run {
                                    setToken(user.accessToken)
                                    setPhone(phone)
                                }
                            }
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
        const val ROUTE_REGISTER = "register"
        const val ROUTE_MAIN = "main"
        const val ROUTE_FAILED = "failed"

        const val DEVICE_TYPE = 1
    }
}