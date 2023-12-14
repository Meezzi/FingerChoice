package com.meezzi.fingerchoice.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val content: String,
    val title: String
)

@Serializable
data class Restaurant(
    val name: String,
    val poiId: String? = "",
    val reviews: List<Review>,
    val score: Float,
    val reviewCount: Int,
    val location: String? = null,
    val imageLocations: List<String>? = null,
    var imageUrl: String? = "",
    var imageUrlList: List<String>? = emptyList(),
)