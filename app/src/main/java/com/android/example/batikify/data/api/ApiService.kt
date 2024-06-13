package com.android.example.batikify.data.api

import com.android.example.batikify.data.response.DetailHistoryResponse
import com.android.example.batikify.data.response.DetailResponse
import com.android.example.batikify.data.response.DetectionResponse
import com.android.example.batikify.data.response.EncyclopediaResponse
import com.android.example.batikify.data.response.HistoryResponse
import com.android.example.batikify.data.response.LoginResponse
import com.android.example.batikify.data.response.NewsResponse
import com.android.example.batikify.data.response.ProfileResponse
import com.android.example.batikify.data.response.RegisterResponse
import okhttp3.MultipartBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

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

    @GET("/user")
    suspend fun getProfile() : ProfileResponse
    @GET("/encyclopedia")
    suspend fun getEncyclopedia() : EncyclopediaResponse
    @GET("/detect/histories")
    suspend fun getHistory() : HistoryResponse
    @GET("/encyclopedia/{id}")
    suspend fun getEncyclopediaById(@Path("id") id: String): DetailResponse
    @GET("/detect/histories/{id}")
    suspend fun getEncyclopediaByHistory(@Path("id") idHistory: String): DetailHistoryResponse
    @GET("/encyclopedia")
    suspend fun getEncyclopediaByName(@Query("search")name : String) : EncyclopediaResponse
    @GET("/news")
    suspend fun getNews(): NewsResponse

    @Multipart
    @POST("/detect")
    suspend fun detectImage(@Part file: MultipartBody.Part): DetectionResponse
}