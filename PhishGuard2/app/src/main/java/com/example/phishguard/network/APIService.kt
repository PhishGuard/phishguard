package com.example.phishguard.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import okhttp3.*
import java.io.IOException
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

// Function to make the API call
// Function to make the API call
fun makeAPICall(url: String, inputValue: String, onSuccess: (Bitmap) -> Unit) {
    println("myinfo: apicall starting")
    val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)// Increase the timeout to 30 seconds
        .build()

    val requestBody = FormBody.Builder()
        .add("inputField", inputValue)
        .build()

    val request = Request.Builder()
        .url(url)
        .post(requestBody)
        .build()
    println("myinfo: apicall sended")
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) {
                println("myinfo: Error !response.isSuccessful")
                throw IOException("Unexpected HTTP code $response")
            }

            val responseBody = response.body()
            responseBody?.let {
                println("myinfo: Success! response.isSuccessful")
                val inputStream = it.byteStream()
                val imageBitmap = BitmapFactory.decodeStream(inputStream)
                println("myinfo: ImageBitmap $ImageBitmap")
                inputStream.close()

                onSuccess(imageBitmap)
            }
        }
    })
}

// Usage: call this function with your API endpoint URL



/*const val BASE_URL = "http://172.28.16.113:5000/"

interface APIService {

    @GET("screenshots")
    suspend fun getScreenshots(): List<Screenshot>

    @GET("newScreenshot")
    suspend fun insertScreenshot(screenshot: Screenshot): List<Screenshot>


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
*/