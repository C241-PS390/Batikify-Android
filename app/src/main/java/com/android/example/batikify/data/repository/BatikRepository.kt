package com.android.example.batikify.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.example.batikify.data.api.BatikApiService
import com.android.example.batikify.data.pref.UserPreference
import com.android.example.batikify.data.response.DataItemEncyclopedia
import com.android.example.batikify.data.response.EncyclopediaResponse

class BatikRepository (
    private val BatikApiService: BatikApiService,
    private val userPreference: UserPreference
){
    fun getSession() = userPreference.getSession()

    suspend fun getEncyclopedia() : EncyclopediaResponse{
        return BatikApiService.getEncyclopedia()
    }
    suspend fun logout() {
        userPreference.logout()
    }
    companion object {
        fun getInstance(
            userPreference: UserPreference,
            apiService: BatikApiService
        ) = BatikRepository(apiService, userPreference)
    }
}