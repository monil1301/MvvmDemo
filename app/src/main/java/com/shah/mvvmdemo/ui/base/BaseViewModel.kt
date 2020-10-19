package com.shah.mvvmdemo.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shah.mvvmdemo.data.network.Resource
import com.shah.mvvmdemo.data.network.UserApi
import com.shah.mvvmdemo.data.repository.BaseRepository
import com.shah.mvvmdemo.data.repository.UserRepository
import com.shah.mvvmdemo.data.response.LoginResponse
import kotlinx.coroutines.launch

abstract class BaseViewModel(private val repository: BaseRepository): ViewModel() {

    suspend fun logout(api: UserApi) = repository.logout(api)
}