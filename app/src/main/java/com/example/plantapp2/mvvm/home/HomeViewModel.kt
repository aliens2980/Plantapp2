package com.example.plantapp2.mvvm.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantapp2.Models.Plant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    init {

    }

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun addPlantToList(plant: Plant) {
        _uiState.value.plants.add(plant)
    }

    fun replaceWholePlantList(plants: MutableList<Plant>) {
        _uiState.update { currentState ->
            currentState.copy(plants = plants)
        }
    }

    fun getPlantJens(): Plant {
        val plantToReturn: Plant = Plant(name = "Jens")
        return plantToReturn
    }


}