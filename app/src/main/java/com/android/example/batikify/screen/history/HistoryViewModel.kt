package com.android.example.batikify.screen.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.batikify.data.repository.BatikRepository
import com.android.example.batikify.data.response.DataItemHistory
import kotlinx.coroutines.launch

class HistoryViewModel(private val batikRepository: BatikRepository) : ViewModel() {

    private var _listHistory = MutableLiveData<List<DataItemHistory>>()
    val listHistory: LiveData<List<DataItemHistory>> = _listHistory

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getHistory()
    }

    fun getHistory() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = batikRepository.getHistory()
                if (response.status == "success" && response.data != null) {
                    _listHistory.value = response.data!!
                    Log.d(TAG,"$response")
                } else {
                    Log.d(TAG, "Response Error: ${response.message}")
                }
            } catch (e: Exception) {
                Log.d(TAG, "Exception Error", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
    companion object{
        private val TAG = "EncyclopediaViewModel"
    }
}