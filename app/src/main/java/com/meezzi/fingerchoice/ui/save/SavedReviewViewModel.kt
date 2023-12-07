package com.meezzi.fingerchoice.ui.save

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.meezzi.fingerchoice.data.repository.ReviewRepository
import com.meezzi.fingerchoice.data.source.local.Review
import kotlinx.coroutines.launch

class SavedReviewViewModel(private val repository: ReviewRepository) : ViewModel() {

    private val _review = MutableLiveData<List<Review>>()
    val review: LiveData<List<Review>> = _review

    fun loadReview() {
        viewModelScope.launch {
            val reviews = repository.getAllReview()
            _review.value = reviews
        }
    }

    companion object {
        fun provideFactory(repository: ReviewRepository) = viewModelFactory {
            initializer {
                SavedReviewViewModel(repository)
            }
        }
    }
}