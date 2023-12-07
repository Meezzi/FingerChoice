package com.meezzi.fingerchoice.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ReviewDao {

    @Insert
    suspend fun insertReview(review: Review)

    @Query("SELECT * FROM review")
    suspend fun getAllReview(): List<Review>
}
