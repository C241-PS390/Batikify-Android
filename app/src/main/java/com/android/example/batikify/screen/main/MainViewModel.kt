package com.android.example.batikify.screen.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.batikify.data.pref.UserModel
import com.android.example.batikify.data.repository.AuthRepository
import com.android.example.batikify.data.response.LoginResponse
import kotlinx.coroutines.launch

class MainViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val TAG = "MainViewModel"

    private val _loginResult = MutableLiveData<LoginResponse>()
    val loginResult: LiveData<LoginResponse> = _loginResult

    private val _isLoading = MutableLiveData<Boolean>()


    init{
        _isLoading.value = false
    }

    fun saveSession (user: UserModel) {
        viewModelScope.launch {
            authRepository.saveSession(user)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = authRepository.login(email, password)
                _loginResult.value = response
                if (response.status == "success") {
                    val token = response.data?.token
                    token?.let {
                        val userModel = UserModel(email, it, true)
                        saveSession(userModel)
                    }
                } else {
                    Log.d(TAG, "Response Error")
                }
            } catch (e: Exception) {
                Log.d(TAG, "Exception Error")
            }
            _isLoading.value = false
        }
    }
}