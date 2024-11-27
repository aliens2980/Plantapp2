package com.example.plantapp2.plants

import android.content.Context
import android.util.Log
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModel
import com.example.plantapp2.data.Plant

import com.example.plantapp2.utils.saveJsonToFile
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class PlantsViewModel(
    private val repository: PlantsRepository = PlantsRepository()
) : ViewModel() {
    private val jsonFormat = Json { prettyPrint = true }

    fun getResponseUsingLiveData() = repository.getResponseFromFirestoreUsingLiveData()

    suspend fun getPlantDetailsByName(name: String): Plant? {
        return repository.getPlantByName(name)
    }

    fun savePlant(context: Context, plant: Plant) {
        try {
            val fileName = "liked_plants.json"
            val file = File(context.filesDir, fileName)

            // Read existing data
            val existingPlants = if (file.exists()) {
                val json = file.readText()
                Json.decodeFromString<List<Plant>>(json)
            } else {
                emptyList()
            }

            val updatedPlants = if (plant.isLiked) {
                // Add or update the plant
                existingPlants.filter { it.name != plant.name } + plant
            } else {
                // Remove the plant
                existingPlants.filter { it.name != plant.name }
            }

            // Save updated data
            val json = Json.encodeToString(updatedPlants)
            file.writeText(json)
            Log.d("savePlant", "Updated favorites: ${updatedPlants.size} plants saved to ${file.absolutePath}")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun getLikedPlants(context: Context): List<Plant> {
        val fileName = "liked_plants.json"
        val file = File(context.filesDir, fileName)
        return if (file.exists()) {
            val json = file.readText()
            Json.decodeFromString(json)
        } else {
            emptyList()
        }
    }
}
