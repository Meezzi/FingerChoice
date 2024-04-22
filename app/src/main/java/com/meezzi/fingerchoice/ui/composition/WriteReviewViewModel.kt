package com.meezzi.fingerchoice.ui.composition

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.meezzi.fingerchoice.data.repository.RestaurantRepository
import com.meezzi.fingerchoice.data.repository.ReviewRepository
import com.meezzi.fingerchoice.data.source.local.Review
import kotlinx.coroutines.launch

class WriteReviewViewModel(
    private val reviewRepository: ReviewRepository,
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {

    val score = MutableLiveData<Float>()
    val title = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val isPrivateChecked = MutableLiveData<Boolean>()
    val taste = MutableLiveData<String>()

    private val date: String = "2020.20.20"
    private val restaurant: String = "식당"
    private val location: String = "위치"

    fun setTaste(value: String) {
        taste.value = value
    }

    fun insertReview(listener: ReviewSaveClickListener) {
        if (isPrivateChecked.value != true) return
        val currentTitle = title.value ?: ""
        val currentContent = content.value ?: ""
        if (!isValidInfo(currentTitle) || !isValidInfo(currentContent)) return

        if (isPrivateChecked.value != true) {
            val reviews = com.meezzi.fingerchoice.data.model.Review(
                currentTitle,
                currentContent
            )
            Log.e("poiId", setPoiId.toString())
            insertReviewToFirebaseDB(setPoiId.toString(), reviews)
        } else insertReviewToLocalDB(listener, currentTitle, currentContent)
    }

    fun insertReviewToFirebaseDB(
        poiId: String,
        reviews: com.meezzi.fingerchoice.data.model.Review,
    ) {
        viewModelScope.launch {
//            restaurantRepository.insertReview(
//                name,
//                poiId,
//                reviews,
//                score.value!!,
//                location,
//            )
        }
    }

    fun insertReviewToLocalDB(
        listener: ReviewSaveClickListener,
        currentTitle: String,
        currentContent: String
    ) {

        val review = Review(
            taste = taste.value!!,
            score = score.value!!,
            title = currentTitle,
            content = currentContent,
            date = date,
            restaurant = restaurant,
            location = location
        )
        viewModelScope.launch {
            reviewRepository.insertReview(review)
            listener.onComplete()
        }
    }

    private fun isValidInfo(value: String): Boolean {
        if (value.isBlank()) {
            return false
        }
        return true
    }

    companion object {
        fun provideFactory(
            reviewRepository: ReviewRepository,
            restaurantRepository: RestaurantRepository,
        ) = viewModelFactory {
            initializer {
                WriteReviewViewModel(reviewRepository, restaurantRepository)
            }
        }
    }
}
