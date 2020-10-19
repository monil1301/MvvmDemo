package com.shah.mvvmdemo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shah.mvvmdemo.data.network.Resource
import com.shah.mvvmdemo.data.repository.UserRepository
import com.shah.mvvmdemo.data.response.LoginResponse
import kotlinx.coroutines.launch

class HomeViewModel(private val userRepository: UserRepository): ViewModel() {

    val _user: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val user: LiveData<Resource<LoginResponse>>
    get() = _user

    suspend fun getUser() {
        _user.value = userRepository.getUser()
    }
}