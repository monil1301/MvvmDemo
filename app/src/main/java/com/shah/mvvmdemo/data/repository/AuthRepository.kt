package com.shah.mvvmdemo.data.repository

import com.shah.mvvmdemo.data.network.AuthApi

class AuthRepository(private val api: AuthApi): BaseRepository()  {

    suspend fun login(email: String, password: String) = safeApoiCall { api.login(email, password) }
}