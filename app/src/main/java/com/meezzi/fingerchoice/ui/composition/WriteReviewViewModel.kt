package com.meezzi.fingerchoice.ui.composition

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.meezzi.fingerchoice.data.repository.ReviewRepository
import com.meezzi.fingerchoice.data.source.local.Review
import kotlinx.coroutines.launch

class WriteReviewViewModel(private val reviewRepository: ReviewRepository) : ViewModel() {

    val score = MutableLiveData<Float>()
    val title = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val isPrivateChecked = MutableLiveData<Boolean>()
    val taste = MutableLiveData<String>()

    private val date: String = "2020.20.20"
    private val restaurant: String = "식당"

    fun setTaste(value: String) {
        taste.value = value
    }

    fun insertReview(listener: ReviewSaveClickListener) {
        if (isPrivateChecked.value != true) return
        val currentTitle = title.value ?: ""
        val currentContent = content.value ?: ""
        if (!isValidInfo(currentTitle) || !isValidInfo(currentContent)) return

        val review = Review(
            taste = taste.value!!,
            score = score.value!!,
            title = currentTitle,
            content = currentContent,
            date = date,
            restaurant = restaurant,
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
            repository: ReviewRepository,
        ) = viewModelFactory {
            initializer {
                WriteReviewViewModel(repository)
            }
        }
    }
}
