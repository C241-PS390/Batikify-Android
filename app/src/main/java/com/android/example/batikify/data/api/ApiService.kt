package com.android.example.batikify.data.api

import com.android.example.batikify.data.response.DetailResponse
import com.android.example.batikify.data.response.EncyclopediaResponse
import com.android.example.batikify.data.response.LoginResponse
import com.android.example.batikify.data.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApiService {

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(
        @Field("fullName") fullName : String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("passwordConfirmation") passwordConfirmation : String,
    ): RegisterResponse

}

interface BatikApiService {

    @GET("/encyclopedia")
    suspend fun getEncyclopedia() : EncyclopediaResponse

    @GET("/encyclopedia/{id}")
    suspend fun getEncyclopediaById(@Path("id") id: String): DetailResponse
}