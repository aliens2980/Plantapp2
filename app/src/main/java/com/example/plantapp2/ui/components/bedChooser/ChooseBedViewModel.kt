package com.example.plantapp2.ui.components.bedChooser


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.plantapp2.Data.Bed
import com.example.plantapp2.Data.LocalPlant
import com.example.plantapp2.Data.generateSamplePlants

class ChoosePlantViewModel : ViewModel() {
    private val _localBed = MutableLiveData<List<Bed>>()
    private val localPlants: LiveData<List<LocalPlant>> = _localPlants

    val bedList: LiveData<List<LocalPlant>> = localPlants.map { it.filter { plant -> plant.isFavorite } }

    private val _showFavoritePlants = MutableLiveData(true)
    val showFavoritePlants: LiveData<Boolean> = _showFavoritePlants

    init {
        populatePlants()
    }

    private fun populatePlants() {
        _localPlants.value = generateSamplePlants()
    }

    fun togglePlantList(showFavorites: Boolean) {
        _showFavoritePlants.value = showFavorites
    }
}