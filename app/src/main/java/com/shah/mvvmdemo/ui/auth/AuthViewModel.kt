package com.shah.mvvmdemo.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shah.mvvmdemo.data.network.Resource
import com.shah.mvvmdemo.data.repository.AuthRepository
import com.shah.mvvmdemo.data.response.LoginResponse
import com.shah.mvvmdemo.data.response.SignupResponse
import com.shah.mvvmdemo.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository) : BaseViewModel(authRepository) {

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = authRepository.login(email, password)
    }

    fun saveAuthToken(token: String) = viewModelScope.launch {
        authRepository.saveAuthToken(token)
    }

    private val _signupResponse: MutableLiveData<Resource<SignupResponse>> = MutableLiveData()
    val signupResponse: LiveData<Resource<SignupResponse>>
        get() = _signupResponse

    fun signup(name: String, email: String, password: String, password_confirmation: String) =
        viewModelScope.launch {
            _signupResponse.value = authRepository.signup(name, email, password, password_confirmation)
        }

}