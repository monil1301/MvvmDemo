package com.shah.mvvmdemo.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiBuilder {
    companion object {
        private const val baseUrl = "http://simplifiedcoding.tech/mywebapp/public/api/"
    }

    fun<Api> buildApi(api: Class<Api>, authToken: String? = null): Api {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        chain.proceed(chain.request().newBuilder().also {
                            it.header("Authorization","Bearer $authToken")
                        }.build())
                    }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}