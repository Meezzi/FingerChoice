package com.meezzi.fingerchoice.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.meezzi.fingerchoice.data.model.Restaurant
import com.meezzi.fingerchoice.data.repository.RestaurantRepository
import kotlinx.coroutines.launch

class ExploreRestaurantViewModel(
    private val repository: RestaurantRepository,
) : ViewModel() {

    private val _restaurants = MutableLiveData<List<Restaurant>>()
    val restaurants: LiveData<List<Restaurant>> = _restaurants

    fun loadRestaurant() {
        viewModelScope.launch {
            val restaurantList = repository.getRestaurant()
            _restaurants.value = restaurantList
        }
    }

    companion object {
        fun provideFactory(
            repository: RestaurantRepository,
        ) = viewModelFactory {
            initializer {
                ExploreRestaurantViewModel(repository)
            }
        }
    }
}