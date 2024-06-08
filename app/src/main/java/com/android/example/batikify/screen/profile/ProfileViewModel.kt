package com.android.example.batikify.screen.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.batikify.data.repository.BatikRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ProfileViewModel(private val batikRepository: BatikRepository) : ViewModel() {

    private val _email = MutableLiveData<String?>()
    val email: MutableLiveData<String?> = _email

    private val _fullName = MutableLiveData<String?>()
    val fullName: MutableLiveData<String?> = _fullName

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    fun getProfile(){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = batikRepository.getProfile()
                _isLoading.value = false
                if (response.status == "success") {
                    val encyclopedia = response.data
                    _email.value = encyclopedia?.email
                    _fullName.value = encyclopedia?.fullName
                } else {
                    Log.e(TAG, "Error: ${response.message}")
                }
            }catch (e: HttpException){
                Log.e(TAG, "Error: ${e.message}")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            batikRepository.logout()
        }
    }


    companion object{
        private val TAG = "ProfileViewModel"
    }
}