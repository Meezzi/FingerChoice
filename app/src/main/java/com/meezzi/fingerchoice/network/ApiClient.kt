package com.meezzi.fingerchoice.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.meezzi.fingerchoice.data.model.Restaurant
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

interface ApiClient {

    @GET("restaurants.json")
    suspend fun getRestaurant(): Response<List<Restaurant>>

    companion object {

        private const val baseUrl =
            "https://fingerchoice-dced2-default-rtdb.asia-southeast1.firebasedatabase.app/"

        fun create(): ApiClient {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            val contentType = "application/json".toMediaType()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(Json.asConverterFactory(contentType))
                .build()
                .create(ApiClient::class.java)
        }
    }
}