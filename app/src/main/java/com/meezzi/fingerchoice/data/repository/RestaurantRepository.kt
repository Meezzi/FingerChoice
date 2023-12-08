package com.meezzi.fingerchoice.data.repository

import com.google.firebase.storage.FirebaseStorage
import com.meezzi.fingerchoice.data.model.Restaurant
import com.meezzi.fingerchoice.network.ApiClient
import kotlinx.coroutines.tasks.await

class RestaurantRepository(
    private val apiClient: ApiClient,
    private val firebaseStorage: FirebaseStorage,
) {

    suspend fun getRestaurant(): List<Restaurant> {
        return try {
            val response = apiClient.getRestaurant()
            if (response.isSuccessful) {
                val restaurantList = response.body()
                restaurantList?.forEach { restaurant ->
                    val updatedImageUrlList = restaurant.imageUrlList?.map { imageUrl ->
                        getDownloadUrl(imageUrl)
                    }
                    restaurant.imageUrlList = updatedImageUrlList
                }
                restaurantList ?: emptyList()

            } else {
                emptyList()
            }

        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getPoiIdRestaurant(poiId: String): Restaurant {
        return try {
            val response = apiClient.getRestaurant()
            if (response.isSuccessful) {
                val restaurants = response.body()
                val restaurant = restaurants?.find { it.poiId == poiId }
                restaurant ?: Restaurant("", "", "", 0, "", "", 0.0)
            } else {
                Restaurant("", "", "", 0, "", "", 0.0)
            }
        } catch (e: Exception) {
            Restaurant("", "", "", 0, "", "", 0.0)
        }
    }

    private suspend fun getDownloadUrl(imageUrl: String): String {
        return firebaseStorage.getReference(imageUrl)
            .downloadUrl
            .await()
            .toString()
    }
}