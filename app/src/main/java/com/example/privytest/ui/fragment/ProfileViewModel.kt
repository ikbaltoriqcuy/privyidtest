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

class ProfileViewModel(val repository: RepositoryAPI): BaseViewModel() {

    init {
        getProfile()
    }

    val message = MutableLiveData<String>()

    fun saveProfile(name: String,
                    gender: String,
                    birthday: String,
                    hometown: String,
                    bio: String) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                isLoading.value = true
                repository.saveProfile(
                    name,
                    gender,
                    birthday,
                    hometown,
                    bio
                ).enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        isLoading.value = false
                        if (response.isSuccessful) {
                            message.value = "Success menyimpan profile"
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


    fun saveEducation(schoolName: String,
                      graduationTime: String) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                isLoading.value = true
                repository.saveEducation(
                    schoolName,
                    graduationTime
                ).enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        isLoading.value = false
                        if (response.isSuccessful) {
                            message.value = "Success menyimpan education"
                        } else {
                            message.value = "Gagal menyimpan education"
                        }
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        message.value = "Gagal menyimpan education"
                    }
                })

            }
        }
    }

    fun saveCareer(position: String,
                   companyName: String,
                   start: String,
                   end: String) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                isLoading.value = true
                repository.saveCareer(
                    position,
                    companyName,
                    start,
                    end
                ).enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        isLoading.value = false
                        if (response.isSuccessful) {
                            message.value = "Success menyimpan karir"
                        } else {
                            message.value = "Gagal menyimpan karir"
                        }
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        message.value = "Gagal menyimpan karir"
                    }
                })

            }
        }
    }


    fun getProfile() {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                isLoading.value = true
                repository.getProfile().enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        isLoading.value = false
                        if (response.isSuccessful) {
                        } else {
                            message.value = "Gagal Mendaptkan Profile"
                        }
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        message.value = "Gagal Mendaptkan Profile"
                    }
                })

            }
        }
    }


    fun logOut() {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                isLoading.value = true
                repository.logout().enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        isLoading.value = false
                        if (response.isSuccessful) {
                            route.value = ROUTE_LOGIN
                        } else {
                            message.value = "Gagal Logout"
                        }
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        message.value = "Gagal Logout"
                    }
                })

            }
        }
    }

    companion object {
        const val ROUTE_LOGIN = "login"
    }
}