package com.example.phishguard.network

import Screenshot
import com.example.phishguard.network.data.AccessInfo
import com.example.phishguard.network.data.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

const val BASE_URL = "http://localhost:5000/"

interface APIService {
    @GET("screenshot")
    suspend fun getScreenshot(): List<Screenshot>

    @GET("screenshots")
    suspend fun getScreenshots(): List<Screenshot>

    @GET("updateScreenshot")
    suspend fun updateScreenshot(): List<AccessInfo>

    @GET("newScreenshot")
    suspend fun insertScreenshot(): List<AccessInfo>

    @GET("deleteScreenshot")
    suspend fun deleteScreenshot(): List<AccessInfo>

    @POST("login")
    suspend fun login(): List<User>

    companion object {
        var apiService: APIService? = null


        fun getInstance(): APIService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }
}
