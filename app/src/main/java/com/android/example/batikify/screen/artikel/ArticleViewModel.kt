package com.android.example.batikify.screen.artikel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.batikify.data.repository.BatikRepository
import com.android.example.batikify.data.response.DataItemNews
import kotlinx.coroutines.launch

class ArticleViewModel(private val batikRepository: BatikRepository): ViewModel() {

    private var _listNews = MutableLiveData<List<DataItemNews>>()
    val listNews: LiveData<List<DataItemNews>> = _listNews

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getNews()
    }

    fun getNews() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = batikRepository.getNews()
                if (response.status == "success" && response.data != null) {
                    _listNews.value = response.data!!
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
        private val TAG = "ArticleViewModel"
    }
}