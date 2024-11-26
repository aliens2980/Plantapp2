package com.example.plantapp2.plants

import androidx.lifecycle.ViewModel
import com.example.plantapp2.data.Plant

class PlantsViewModel (
    private val repository: PlantsRepository = PlantsRepository()
): ViewModel() {

    fun getResponseUsingLiveData() = repository.getResponseFromFirestoreUsingLiveData()

    suspend fun getPlantDetailsByName(name: String): Plant? {
        return repository.getPlantByName(name)
    }
}