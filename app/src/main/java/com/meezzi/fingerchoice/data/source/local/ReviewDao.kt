package com.meezzi.fingerchoice.data.source.local

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface ReviewDao {

    @Insert
    suspend fun insertReview(review: Review)
}
