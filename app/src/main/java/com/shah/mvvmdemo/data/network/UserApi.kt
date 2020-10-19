package com.shah.mvvmdemo.data.network

import com.shah.mvvmdemo.data.response.LoginResponse
import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

   @GET("user")
    suspend fun getUser() : LoginResponse

    @POST("logout")
    suspend fun logout() : ResponseBody
}