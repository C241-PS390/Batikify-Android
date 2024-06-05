package com.android.example.batikify.data.api

import com.android.example.batikify.data.response.LoginResponse
import com.android.example.batikify.data.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApiService {

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse
}

interface BatikApiService {

}