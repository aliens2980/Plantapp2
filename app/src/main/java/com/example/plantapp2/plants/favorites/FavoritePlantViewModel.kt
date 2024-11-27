package com.example.plantapp2.plants.favorites

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plantapp2.data.Plant
import com.example.plantapp2.data.localData.LocalPlant
import com.example.plantapp2.utils.toLocalPlant
import kotlinx.serialization.json.Json
import java.io.File



class FavoritePlantsViewModel(private val context: Context) : ViewModel() {

    private val _favoritePlants = MutableLiveData<List<LocalPlant>>()
    val favoritePlants: LiveData<List<LocalPlant>> get() = _favoritePlants

    private val jsonFileName = "liked_plants.json"

    init {
        loadFavoritePlants()
    }

    private fun loadFavoritePlants() {
        try {
            val file = File(context.filesDir, jsonFileName)
            if (file.exists()) {
                val json = file.readText()
                val plants = Json.decodeFromString<List<Plant>>(json).map { it.toLocalPlant() }
                _favoritePlants.value = plants
            } else {
                _favoritePlants.value = emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _favoritePlants.value = emptyList()
        }
    }

    private fun loadPrioritisedList(listOfFavPlants: List<LocalPlant>) {
        listOfFavPlants.forEach{plant ->
            plant.priority.forEach{ element ->
                result = (result + element).toMutableList()
            }
        }
        result = result.distinct().toMutableList()
    }

}

