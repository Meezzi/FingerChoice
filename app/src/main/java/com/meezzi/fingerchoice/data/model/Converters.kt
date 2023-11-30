package com.meezzi.fingerchoice.data.model

import androidx.room.TypeConverter
import com.meezzi.fingerchoice.data.source.local.Review
import com.squareup.moshi.Moshi

class Converters {

    private val moshi = Moshi.Builder().build()

    @TypeConverter
    fun reviewToJson(review: Review): String {
        val jsonAdapter = moshi.adapter(Review::class.java)
        return jsonAdapter.toJson(review)
    }

    @TypeConverter
    fun reviewFromJson(json: String): Review? {
        val jsonAdapter = moshi.adapter(Review::class.java)
        return jsonAdapter.fromJson(json)
    }

}