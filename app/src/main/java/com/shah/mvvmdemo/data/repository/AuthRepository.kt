package com.shah.mvvmdemo.repository

import com.shah.mvvmdemo.network.AuthApi

class AuthRepository(private val api: AuthApi): BaseRepository()  {

    suspend fun login(email: String, password: String) = safeApoiCall { api.login(email, password) }
}