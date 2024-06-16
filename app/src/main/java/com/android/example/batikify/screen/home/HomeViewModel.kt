package com.android.example.batikify.screen.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.android.example.batikify.data.pref.UserModel
import com.android.example.batikify.data.repository.BatikRepository
import com.android.example.batikify.data.response.DataItemHistory
import com.android.example.batikify.data.response.DataItemNews
import kotlinx.coroutines.launch

class HomeViewModel(private val batikRepository: BatikRepository) : ViewModel() {

    private var _listHistory = MutableLiveData<List<DataItemHistory>?>()
    val listHistory: LiveData<List<DataItemHistory>?> = _listHistory

    private var _listNews = MutableLiveData<List<DataItemNews>?>()
    val listNews: LiveData<List<DataItemNews>?> = _listNews

    private var _isLoadingHistory = MutableLiveData<Boolean>()
    val isLoadingHistory: LiveData<Boolean> = _isLoadingHistory

    private var _isLoadingArticle = MutableLiveData<Boolean>()
    val isLoadingArticle: LiveData<Boolean> = _isLoadingArticle

    fun getSession(): LiveData<UserModel> {
        return batikRepository.getSession().asLiveData()
    }

    init {
        getHistory()
        getNews()
    }

    fun getHistory() {
        viewModelScope.launch {
            _isLoadingHistory.value = true
            try {
                val response = batikRepository.getHistory()
                if (response.status == "success" && response.data != null) {
                    _listHistory.value = response.data
                    Log.d(TAG,"$response")
                } else {
                    Log.d(TAG, "Response Error: ${response.message}")
                }
            } catch (e: Exception) {
                Log.d(TAG, "Exception Error", e)
            } finally {
                _isLoadingHistory.value = false
            }
        }
    }

    fun getNews(){
        viewModelScope.launch {
            _isLoadingArticle.value = true
            try {
                val response = batikRepository.getNews()
                if (response.status == "success" && response.data != null) {
                    _listNews.value = response.data
                    Log.d(TAG,"$response")
                } else {
                    Log.d(TAG, "Response Error: ${response.message}")
                }
            } catch (e: Exception) {
                Log.d(TAG, "Exception Error", e)
            } finally {
                _isLoadingArticle.value = false
            }
        }
    }

    companion object{
        private val TAG = "HomeViewModel"
    }
}