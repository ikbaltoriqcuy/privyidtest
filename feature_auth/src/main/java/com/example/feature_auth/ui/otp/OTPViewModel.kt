package com.example.feature_auth.ui.otp

import androidx.lifecycle.MutableLiveData
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

class OTPViewModel(val repository: RepositoryAPI): BaseViewModel() {

    var otpValue = "    "
    var message = MutableLiveData<String>()

    init { resendOtp() }

    fun matchOtp() {

        if (otpValue.length < 4) {
            message.value = "OTP belum terisi"
            return
        }

        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                isLoading.value = true
                repository.matchOtp(
                    Hawkutil.getUserID(),
                    otpValue,
                ).enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        isLoading.value = false
                        if (response.isSuccessful) {
                            Hawkutil.setFinishRegister(true)
                            route.value = ROUTE_MAIN
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

    fun resendOtp() {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                isLoading.value = true
                repository.resendOtp(
                    Hawkutil.getPhone()
                ).enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        isLoading.value = false
                        if (response.isSuccessful) {
                            route.value = ROUTE_MAIN
                            response.body()?.let {
                                val user = Gson().fromJson(
                                    it.getAsJsonObject("data").getAsJsonObject("user").toString(),
                                    User::class.java
                                )
                                Hawkutil.setToken(user.accessToken)
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