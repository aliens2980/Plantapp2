package com.example.plantapp2.mvvm.home

import androidx.lifecycle.ViewModel
import com.example.plantapp2.data.Plant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun addPlantToList(plant: Plant) {
        _uiState.value.plants.add(plant)
    }

    fun getPlantJens(): Plant {
        val plantToReturn: Plant = Plant(name = "Jens")
        return plantToReturn
    }
}