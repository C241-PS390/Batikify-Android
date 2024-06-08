package com.android.example.batikify.screen.classification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.example.batikify.data.repository.BatikRepository

class ClassificationViewModel(private val batikRepository: BatikRepository) : ViewModel() {

    private val TAG = "ClassificationViewModel"

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

}