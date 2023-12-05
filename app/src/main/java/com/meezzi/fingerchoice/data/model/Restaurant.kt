package com.meezzi.fingerchoice.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Restaurant(
    val name: String,
    val review: Int,
    val score: Double,
    val key: String = "",
    val location: String? = "",
    val imageLocations: List<String>? = null,
    var imageUrl: String? = "",
    var imageUrlList: List<String>? = emptyList(),
)