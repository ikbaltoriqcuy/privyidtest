package com.example.api_helper.apiservice

import com.google.gson.JsonObject
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface ServiceApi {

    @FormUrlEncoded
    @GET("v1/message/{user_id}")
    fun getMessage(
        @Header("Authorization") auth: String,
        @Path("user_id") userId: String
    ): Call<JsonObject>

    @FormUrlEncoded
    @POST("v1/message/send")
    fun sendMessage(
        @Header("Authorization") auth: String,
        @Field("user_id") userId: String,
        @Field("message") message: String
    ): Call<JsonObject>

    @FormUrlEncoded
    @POST("v1/notification/{grub_id}/{token}")
    fun sendTokenMessage(
        @Path("grub_id") grubId: Int,
        @Path("token") token: Int
    ): Call<JsonObject>

    @FormUrlEncoded
    @GET("v1/oauth/credentials")
    fun getCredential(
        @Query("access_token") accessToken: String
    ): Call<JsonObject>

    @FormUrlEncoded
    @POST("v1/oauth/sign_in")
    fun signIn(
        @Field("phone") phone: String,
        @Field("password") password: String,
        @Field("latlong") latlong: String,
        @Field("device_token") deviceToken: String,
        @Field("device_type") deviceType: Int
    ): Call<JsonObject>

    @FormUrlEncoded
    @POST("v1/oauth/revoke")
    fun signOut(
        @Field("access_token") accessToken: String,
        @Field("confirm") confirm: Int
    ): Call<JsonObject>

    @FormUrlEncoded
    @POST("v1/profile/career")
    fun updateCareer(
        @Header("Authorization") auth: String,
        @Field("position") position: String,
        @Field("company_name") companyName: String,
        @Field("starting_from") startingFrom: Date,
        @Field("ending_in") endingIn: Date
    ): Call<JsonObject>

    @FormUrlEncoded
    @POST("v1/profile/education")
    fun updateEducation(
        @Header("Authorization") auth: String,
        @Field("school_name") position: String,
        @Field("graduation_time") companyName: Date
    ): Call<JsonObject>

    @FormUrlEncoded
    @POST("v1/profile")
    fun updateProfile(
        @Header("Authorization") auth: String,
        @Field("name") name: String,
        @Field("gender") gender: Int,
        @Field("birthday") birthday: Date,
        @Field("hometown") hometown: String,
        @Field("bio") bio: String
    ): Call<JsonObject>

    @FormUrlEncoded
    @GET("v1/profile/me")
    fun getProfile(
        @Header("Authorization") auth: String,
    ): Call<JsonObject>

    @FormUrlEncoded
    @POST("v1/register/otp/request")
    fun resendOTP(
        @Field("phone") phone: String,
    ): Call<JsonObject>

    @FormUrlEncoded
    @POST("v1/register/otp/match")
    fun checkOTP(
        @Field("user_id") userId: String,
        @Field("otp_code") otpCode: String,
    ): Call<JsonObject>

    @FormUrlEncoded
    @POST("v1/register")
    fun register(
        @Field("phone") phone: String,
        @Field("password") password: String,
        @Field("country") country: String,
        @Field("latlong") latlong: String,
        @Field("device_token") deviceToken: String,
        @Field("device_type") deviceType: Int
    ): Call<JsonObject>

    @FormUrlEncoded
    @POST("v1/uploads/cover")
    fun uploadCover(
        @Header("Authorization") auth: String,
        @Field("image") img: MultipartBody.Part
    ): Call<JsonObject>

    @FormUrlEncoded
    @POST("v1/uploads/profile")
    fun uploadProfile(
        @Header("Authorization") auth: String,
        @Field("image") img: MultipartBody.Part
    ): Call<JsonObject>
}