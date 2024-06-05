package com.android.example.batikify.data.injection

import android.content.Context
import com.android.example.batikify.data.api.ApiConfig
import com.android.example.batikify.data.pref.UserPreference
import com.android.example.batikify.data.pref.dataStore
import com.android.example.batikify.data.repository.BatikRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object BatikInjection {
    fun provideStoryRepository(context: Context): BatikRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val storyApiService = ApiConfig.getBatikApiService(user.token)
        return BatikRepository.getInstance(pref,storyApiService)
    }

}