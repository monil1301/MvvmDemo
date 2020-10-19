package com.shah.mvvmdemo.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiBuilder {
    companion object {
        private const val baseUrl = "http://simplifiedcoding.tech/mywebapp/public/api/"
    }

    fun<Api> buildApi(api: Class<Api>): Api {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}