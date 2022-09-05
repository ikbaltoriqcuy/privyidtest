package com.example.feature_auth.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("user_status")
    val userStatus: String,
    @SerializedName("user_type")
    val userType: String,
    @SerializedName("sugar_id")
    val sugarId: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("user_device")
    val userDevice: UserDevice
)

data class UserDevice(
    @SerializedName("device_token")
    val deviceToken: String,
    @SerializedName("device_type")
    val deviceType: String,
    @SerializedName("device_status")
    val deviceStatus: String,
)