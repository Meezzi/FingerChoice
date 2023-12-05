package com.meezzi.fingerchoice.data.repository

import com.meezzi.fingerchoice.data.source.local.Review
import com.meezzi.fingerchoice.data.source.local.ReviewDatabase

class ReviewRepository(database: ReviewDatabase) {

    private val reviewDao = database.reviewDao()

    suspend fun insertReview(review: Review) {
        reviewDao.insertReview(review)
    }
}
