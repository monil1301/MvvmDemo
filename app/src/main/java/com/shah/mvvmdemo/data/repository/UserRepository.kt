package com.shah.mvvmdemo.data.repository

import com.shah.mvvmdemo.data.network.UserApi

class UserRepository(private val userApi: UserApi): BaseRepository() {

    suspend fun getUser() = safeApoiCall { userApi.getUser() }
}