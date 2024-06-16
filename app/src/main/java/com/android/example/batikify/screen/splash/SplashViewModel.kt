package com.android.example.batikify.screen.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.android.example.batikify.data.pref.UserModel
import com.android.example.batikify.data.repository.AuthRepository

class SplashViewModel(private val authRepository: AuthRepository) : ViewModel() {

    fun getSession(): LiveData<UserModel> {
        return authRepository.getSession().asLiveData()
    }
}