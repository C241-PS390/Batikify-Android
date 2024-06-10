package com.android.example.batikify.screen.ensiklopedia

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.android.example.batikify.data.pref.UserModel
import com.android.example.batikify.data.repository.BatikRepository
import com.android.example.batikify.data.response.DataItemEncyclopedia
import kotlinx.coroutines.launch

class EnsiklopediaViewModel(private val batikRepository: BatikRepository)  : ViewModel(){

    private var _listEncyclopedia = MutableLiveData<List<DataItemEncyclopedia>>()
    val listEncyclopedia: LiveData<List<DataItemEncyclopedia>> = _listEncyclopedia

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getEncyclopedia()
    }

    fun getEncyclopedia() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = batikRepository.getEncyclopedia()
                if (response.status == "success" && response.data != null) {
                    _listEncyclopedia.value = response.data!!
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