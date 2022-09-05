package com.example.privytest.ui.fragment

import androidx.lifecycle.MutableLiveData
import com.example.api_helper.apiservice.RepositoryAPI
import com.example.common_base.BaseViewModel
import com.example.feature_auth.model.User
import com.example.feature_auth.ui.otp.OTPViewModel
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

class MessageViewModel(val repository: RepositoryAPI): BaseViewModel() {

    init {
        sendMessage()
        getMessage()
    }

    val message = MutableLiveData<String>()

    fun sendMessage() {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                isLoading.value = true
                repository.sendMessage(
                    Hawkutil.getUserID(),
                    "test"
                ).enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        isLoading.value = false
                        if (response.isSuccessful) {
                        } else {
                            message.value = "Gagal menyimpan profile"
                        }
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        message.value = "Gagal menyimpan profile"
                    }
                })

            }
        }
    }


    fun getMessage() {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                isLoading.value = true
                repository.getMessage(
                    Hawkutil.getUserID()
                ).enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        isLoading.value = false
                        if (response.isSuccessful) {
                        } else {
                            message.value = "Gagal menyimpan profile"
                        }
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        message.value = "Gagal menyimpan profile"
                    }
                })

            }
        }
    }


    companion object {
        const val ROUTE_LOGIN = "login"
    }
}