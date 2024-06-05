package com.android.example.batikify.data.injection

import android.content.Context
import com.android.example.batikify.data.api.ApiConfig
import com.android.example.batikify.data.pref.UserPreference
import com.android.example.batikify.data.pref.dataStore
import com.android.example.batikify.data.repository.AuthRepository

object AuthInjection {
    fun provideAuthRepository(context: Context): AuthRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val authApiService = ApiConfig.getAuthApiService()
        return AuthRepository(pref, authApiService)
    }
}