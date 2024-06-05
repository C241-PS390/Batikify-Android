package com.android.example.batikify.data.repository

import com.android.example.batikify.data.api.BatikApiService
import com.android.example.batikify.data.pref.UserPreference

class BatikRepository (
    private val BatikApiService: BatikApiService,
    private val userPreference: UserPreference
){

    companion object {
        fun getInstance(
            userPreference: UserPreference,
            apiService: BatikApiService
        ) = BatikRepository(apiService, userPreference)
    }
}