package com.android.example.batikify.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.batikify.data.repository.AuthRepository
import com.android.example.batikify.data.response.ErrorResponse
import com.android.example.batikify.data.response.RegisterResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SignUpViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _status = MutableLiveData<String?>()
    val status: LiveData<String?> get() = _status

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> get() = _message

    private val _errors = MutableLiveData<List<ErrorResponse>>()
    val errors: LiveData<List<ErrorResponse>> get() = _errors

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun register(fullName: String, email: String, password: String, passwordConfirm: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.register(fullName, email, password, passwordConfirm)
                _status.value = response.status

                if (response.status == "success") {
                    _message.value = response.message
                    _errors.value = emptyList()
                } else {
                    _message.value = response.message
                    _errors.value = response.errors ?: emptyList()
                }
            } catch (e: Exception) {
                if (e is HttpException) {
                    _status.value = "fail"
                    val jsonInString = e.response()?.errorBody()?.string()
                    val errorBody = Gson().fromJson(jsonInString, RegisterResponse::class.java)
                    _errors.value = errorBody.errors ?: emptyList()
                    _message.value = errorBody.message ?: "Unknown error"
                } else {
                    _status.value = "fail"
                    _message.value = "Error. Please try again!"
                    _errors.value = emptyList()
                }
            } finally {
                _isLoading.value = false
            }
        }
    }
}
