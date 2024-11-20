package com.example.plantapp2.ui.components

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.plantapp2.Data.LocalPlant


class MainPlantDragViewModel : ViewModel() {
    private val _localPlants = MutableLiveData<List<LocalPlant>>()
    val localPlants: LiveData<List<LocalPlant>> = _localPlants

    val favoritePlants: LiveData<List<LocalPlant>> = localPlants.map { it.filter { plant -> plant.isFavorite } }
    val compatiblePlants: LiveData<List<LocalPlant>> = localPlants.map { it.filter { plant -> !plant.isFavorite } }

    private val _showFavoritePlants = MutableLiveData(true) // Default to showing favorite plants
    val showFavoritePlants: LiveData<Boolean> = _showFavoritePlants

    fun togglePlantList(showFavorites: Boolean) {
        _showFavoritePlants.value = showFavorites
    }

    fun updateLocalPlants(newPlants: List<LocalPlant>) {
        _localPlants.value = newPlants
    }
}
