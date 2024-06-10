package com.android.example.batikify.screen.classification

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.batikify.data.repository.BatikRepository
import com.android.example.batikify.data.response.DetectionResponse
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException

class ClassificationViewModel(private val batikRepository: BatikRepository) : ViewModel() {

    private val TAG = "ClassificationViewModel"

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val uploadStatus: LiveData<Boolean> get() = _uploadStatus
    private val _uploadStatus = MutableLiveData<Boolean>()

    private val _detectionResponse = MutableLiveData<DetectionResponse>()
    val detectionResponse: LiveData<DetectionResponse> = _detectionResponse
    fun uploadImage(
        multipartBody: MultipartBody.Part,
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = batikRepository.detectImage(multipartBody)
                _isLoading.value = false
                _uploadStatus.value = true
                _detectionResponse.value = response
                Log.d(TAG,"Berhasil upload Batik: ${response}")
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                errorBody?.let {
                    Log.d(TAG,"Error $it")
                    _isLoading.value = false
                    _uploadStatus.value = false
                } ?: "Unknown error occurred"
            }
        }
    }
}