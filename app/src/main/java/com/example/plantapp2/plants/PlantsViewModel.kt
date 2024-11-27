package com.example.plantapp2.plants

import android.content.Context
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModel
import com.example.plantapp2.data.Plant

import com.example.plantapp2.utils.saveJsonToFile
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PlantsViewModel(
    private val repository: PlantsRepository = PlantsRepository()
) : ViewModel() {
    private val jsonFormat = Json { prettyPrint = true }

    fun getResponseUsingLiveData() = repository.getResponseFromFirestoreUsingLiveData()

    suspend fun getPlantDetailsByName(name: String): Plant? {
        return repository.getPlantByName(name)
    }

    fun saveLikedPlants(context: Context, plants: List<Plant>) {
        val likedPlants = plants.filter { it.isLiked }
        if (likedPlants.isNotEmpty()) {
            val json = jsonFormat.encodeToString(likedPlants)
            saveJsonToFile(context, "liked_plants.json", json)
            println("Liked plants saved: $json")
        } else {
            println("No liked plants to save.")
        }
    }
}
