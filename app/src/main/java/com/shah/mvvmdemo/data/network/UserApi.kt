package com.shah.mvvmdemo.data.network

import com.shah.mvvmdemo.data.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

   @GET("user")
    suspend fun getUser() : LoginResponse
}