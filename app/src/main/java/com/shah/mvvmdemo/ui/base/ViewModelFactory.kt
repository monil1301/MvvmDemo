package com.shah.mvvmdemo.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shah.mvvmdemo.data.repository.AuthRepository
import com.shah.mvvmdemo.data.repository.BaseRepository
import com.shah.mvvmdemo.data.repository.UserRepository
import com.shah.mvvmdemo.ui.auth.AuthViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: BaseRepository) :  ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthRepository::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(UserRepository::class.java) -> (repository as UserRepository) as T
            else -> throw IllegalArgumentException("ViewModalClass not found")
        }
    }
}