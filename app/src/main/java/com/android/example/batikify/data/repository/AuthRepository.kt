package com.android.example.batikify.data.repository

import android.util.Log
import com.android.example.batikify.data.api.AuthApiService
import com.android.example.batikify.data.pref.UserModel
import com.android.example.batikify.data.pref.UserPreference
import com.android.example.batikify.data.response.LoginResponse
import com.android.example.batikify.data.response.RegisterResponse

class AuthRepository(
    private val userPreference: UserPreference,
    private val authApiService: AuthApiService
) {
    private val TAG = "AuthRepo"

    fun getSession() = userPreference.getSession()

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    suspend fun login(email: String, password: String): LoginResponse {
        try {
            val response = authApiService.login(email, password)
            if (response.status == "success") {
                response.data?.token?.let { token ->
                    userPreference.saveSession(UserModel(email = email, token = token, isLogin = true))
                } ?: throw Exception("Token is null")
            } else {
                Log.e(TAG, "Login failed: ${response.message}")
            }
            return response
        } catch (e: Exception) {
            Log.e(TAG, "Login error: ${e.message}")
            throw e
        }
    }

    suspend fun register(
        fullName: String,
        email: String,
        password: String,
        passwordConfirmation:String
    ): RegisterResponse {
        try {
            val response = authApiService.register(fullName, email, password, passwordConfirmation)
            return response
        } catch (e: Exception) {
            Log.e(TAG, "Sign up error: ${e.message}")
            throw e
        }
    }
}