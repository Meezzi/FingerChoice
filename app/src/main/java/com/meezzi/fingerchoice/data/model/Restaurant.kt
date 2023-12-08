package com.meezzi.fingerchoice.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Restaurant(
    val key: String,
    val name: String,
    val poiId: String = "",
    val reviewCount: Int,
    val reviewTitle: String,
    val reviewContent: String,
    val score: Double,
    val location: String? = "",
    val imageLocations: List<String>? = null,
    var imageUrl: String? = "",
    var imageUrlList: List<String>? = emptyList(),
)