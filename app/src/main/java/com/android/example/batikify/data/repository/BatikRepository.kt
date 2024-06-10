package com.android.example.batikify.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.example.batikify.data.api.BatikApiService
import com.android.example.batikify.data.pref.UserPreference
import com.android.example.batikify.data.response.DataItemEncyclopedia
import com.android.example.batikify.data.response.DetailHistoryResponse
import com.android.example.batikify.data.response.DetailResponse
import com.android.example.batikify.data.response.DetectionResponse
import com.android.example.batikify.data.response.EncyclopediaResponse
import com.android.example.batikify.data.response.HistoryResponse
import com.android.example.batikify.data.response.NewsResponse
import com.android.example.batikify.data.response.ProfileResponse
import okhttp3.MultipartBody

class BatikRepository (
    private val BatikApiService: BatikApiService,
    private val userPreference: UserPreference
){
    fun getSession() = userPreference.getSession()

    suspend fun getEncyclopedia() : EncyclopediaResponse{
        return BatikApiService.getEncyclopedia()
    }
    suspend fun getHistory() : HistoryResponse{
        return BatikApiService.getHistory()
    }
    suspend fun detectImage(file: MultipartBody.Part) : DetectionResponse{
        return BatikApiService.detectImage(file)
    }
    suspend fun getProfile() : ProfileResponse{
        return BatikApiService.getProfile()
    }
    suspend fun getEncyclopediaById(id: String) : DetailResponse{
        return BatikApiService.getEncyclopediaById(id)
    }
    suspend fun getEncyclopediaByHistory(idHistory : String): DetailHistoryResponse{
        return BatikApiService.getEncyclopediaByHistory(idHistory)
    }
    suspend fun getNews(): NewsResponse{
        return BatikApiService.getNews()
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