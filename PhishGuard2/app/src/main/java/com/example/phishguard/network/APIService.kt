package com.example.phishguard.network

import com.example.phishguard.network.data.AccessInfo
import com.example.phishguard.network.data.Screenshot
import com.example.phishguard.network.data.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

import okhttp3.*
import java.io.IOException
import java.io.FileOutputStream

// Function to make the API call
// Function to make the API call
fun makeAPICall(url: String, inputValue: String) {
    val client = OkHttpClient()

    // Create the request body with the input field value
    val requestBody = FormBody.Builder()
        .add("inputField", inputValue)
        .build()

    val request = Request.Builder()
        .url(url)
        .post(requestBody) // Use POST method to send the data
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            // Handle API call failure
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) {
                // Handle unsuccessful response
                throw IOException("Unexpected HTTP code $response")
            }

            // Get the image from the response body
            val responseBody = response.body
            responseBody?.let {
                // Save the image to a file
                val fileOutputStream = FileOutputStream("image.jpg")
                it.byteStream().copyTo(fileOutputStream)
                fileOutputStream.close()
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