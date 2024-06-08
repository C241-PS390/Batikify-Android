package com.android.example.batikify.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.android.example.batikify.data.pref.UserModel
import com.android.example.batikify.data.repository.BatikRepository

class HomeViewModel(private val batikRepository: BatikRepository) : ViewModel() {

    fun getSession(): LiveData<UserModel> {
        return batikRepository.getSession().asLiveData()
    }
}