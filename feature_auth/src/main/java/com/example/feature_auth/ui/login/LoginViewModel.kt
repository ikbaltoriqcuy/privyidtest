package com.example.feature_auth.ui.login

import android.os.Build
import com.example.api_helper.apiservice.RepositoryAPI
import com.example.common_base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(val repository: RepositoryAPI): BaseViewModel() {

    fun login(phone: String, password: String) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                repository.login(
                        phone,
                        password,
                        "0",
                        phone + Build.DEVICE + Build.ID,
                        DEVICE_TYPE
                ).enqueue(object : Callback<JSONObject> {
                    override fun onResponse(
                        call: Call<JSONObject>,
                        response: Response<JSONObject>
                    ) {
                        if (response.isSuccessful) {
                            route.value = ROUTE_MAIN
                        } else {
                            route.value = ROUTE_FAILED
                        }
                    }

                    override fun onFailure(call: Call<JSONObject>, t: Throwable) {
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