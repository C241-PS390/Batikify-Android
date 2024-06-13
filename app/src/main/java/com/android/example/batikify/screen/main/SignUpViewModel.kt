package com.android.example.batikify.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.batikify.data.repository.AuthRepository
import com.android.example.batikify.data.response.ErrorResponse
import kotlinx.coroutines.launch

class SignUpViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _status = MutableLiveData<String?>()
    val status: LiveData<String?> get() = _status

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> get() = _message

    private val _errors = MutableLiveData<List<ErrorResponse>>()
    val errors: LiveData<List<ErrorResponse>> get() = _errors

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun register(fullName: String, email: String, password: String, passwordConfirm: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.register(fullName, email, password, passwordConfirm)
                _status.value = response.status
                if (_status.value == "success") {
                    _message.value = response.message
                    _errors.value = emptyList()
                } else {
                    _errors.value = response.errors ?: emptyList()
                }
            } catch (e: Exception) {
                _status.value = "fail"
                _message.value = "An error occurred. Please try again!"
                _errors.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}