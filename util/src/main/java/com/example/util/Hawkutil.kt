package com.example.util

import android.content.Context
import com.orhanobut.hawk.Hawk

object Hawkutil {
    const val KEY_USER_ID = "user id"
    const val KEY_TOKEN = "token"
    const val KEY_PHONE = "phone"
    const val KEY_STATUS_FINISH_REGIS = "finish regis"

    fun build(context: Context) {
        Hawk.init(context).build()
    }

    fun setUserID(value: String) {
        Hawk.put(KEY_USER_ID, value)
    }

    fun getUserID(): String =
        Hawk.get<String?>(KEY_USER_ID).orEmpty()


    fun setToken(value: String) {
        Hawk.put(KEY_TOKEN, value)
    }

    fun getToken(): String =
        Hawk.get<String?>(KEY_TOKEN).orEmpty()

    fun setPhone(value: String) {
        Hawk.put(KEY_PHONE, value)
    }

    fun getPhone(): String =
        Hawk.get<String?>(KEY_PHONE).orEmpty()


    fun setFinishRegister(value: Boolean) {
        Hawk.put(KEY_STATUS_FINISH_REGIS, value)
    }

    fun getFinishRegsiter(): Boolean =
        Hawk.get<Boolean?>(KEY_STATUS_FINISH_REGIS) ?: false

    fun clearAll() {
        Hawk.deleteAll()
    }
}