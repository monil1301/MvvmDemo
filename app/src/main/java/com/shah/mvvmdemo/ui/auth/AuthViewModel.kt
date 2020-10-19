package com.shah.mvvmdemo.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shah.mvvmdemo.data.network.Resource
import com.shah.mvvmdemo.data.repository.AuthRepository
import com.shah.mvvmdemo.data.response.LoginResponse
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginResponse.value = authRepository.login(email, password)
    }
}