package com.shah.mvvmdemo.data.repository

import com.shah.mvvmdemo.data.UserPreferences
import com.shah.mvvmdemo.data.network.AuthApi

class AuthRepository(private val api: AuthApi, private val preferences: UserPreferences) :
    BaseRepository() {

    suspend fun login(email: String, password: String) = safeApoiCall { api.login(email, password) }

    suspend fun saveAuthToken(token: String) {
        preferences.saveAuthToken(token)
    }

    suspend fun signup(name: String, email: String, password: String, password_confirmation: String) =
        safeApoiCall { api.signup(name, email, password, password_confirmation) }
}