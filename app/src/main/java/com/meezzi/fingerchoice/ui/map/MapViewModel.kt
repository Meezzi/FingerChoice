package com.meezzi.fingerchoice.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.meezzi.fingerchoice.data.model.Restaurant
import com.meezzi.fingerchoice.data.repository.RestaurantRepository
import kotlinx.coroutines.launch

class MapViewModel(
    private val repository: RestaurantRepository,
) : ViewModel() {

    private val _restaurants = MutableLiveData<Restaurant>()
    val restaurants: LiveData<Restaurant> = _restaurants

    fun loadRestaurant(poiId: String) {
        viewModelScope.launch {
            val restaurant = repository.getPoiIdRestaurant(poiId)
            _restaurants.postValue(restaurant)
        }
    }

    companion object {
        fun provideFactory(
            repository: RestaurantRepository,
        ) = viewModelFactory {
            initializer {
                MapViewModel(repository)
            }
        }
    }
}