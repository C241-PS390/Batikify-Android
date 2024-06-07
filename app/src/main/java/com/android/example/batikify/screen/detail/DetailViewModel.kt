package com.android.example.batikify.screen.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.batikify.data.repository.BatikRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DetailViewModel(private val  batikRepository: BatikRepository) : ViewModel(){

    companion object{
        private val TAG = "DetailViewModel"
    }
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _photoUrl = MutableLiveData<String?>()
    val photoUrl : MutableLiveData<String?> = _photoUrl

    private val _name = MutableLiveData<String?>()
    val name: MutableLiveData<String?> = _name

    private val _origin = MutableLiveData<String?>()
    val origin: MutableLiveData<String?> = _origin

    private val _description = MutableLiveData<String?>()
    val description: MutableLiveData<String?> = _description

    fun display(id: String){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = batikRepository.getEncyclopediaById(id)
                _isLoading.value = false
                if (response.status == "success") {
                    val encyclopedia = response.data
                    _origin.value = encyclopedia?.origin
                    _name.value = encyclopedia?.name
                    _description.value = encyclopedia?.description
//                    _photoUrl.value = encyclopedia?.photoUrl
                    Log.d(TAG,"$response")
                } else {
                    Log.e(TAG, "Error: ${response.message}")
                }
            }catch (e: HttpException){

            }
        }
    }
}