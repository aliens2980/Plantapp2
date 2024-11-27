
package com.example.plantapp2.favorite

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(private val context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("PlantAppPrefs", Context.MODE_PRIVATE)

    fun saveLikeState(plantName: String, isLiked: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(plantName, isLiked)
        editor.apply()
    }

    fun getLikeState(plantName: String): Boolean {
        return sharedPreferences.getBoolean(plantName, false)  // Default to false if not found
    }

    // Save a plant to the favorites
    fun saveFavoritePlant(plantName: String, isLiked: Boolean) {
        val sharedPrefs = context.getSharedPreferences("plant_preferences", Context.MODE_PRIVATE)
        sharedPrefs.edit().putBoolean(plantName, isLiked).apply()
    }

    fun getFavoritePlants(context: Context): List<String> {
        val favoritePlantsSet = sharedPreferences.getStringSet("favorite_plants", mutableSetOf()) ?: mutableSetOf()
        return favoritePlantsSet.toList()
    }

    // Function to add or remove a plant from favorites
    fun toggleFavorite(plantName: String, isLiked: Boolean) {
        val favoritePlants = sharedPreferences.getStringSet("favorite_plants", mutableSetOf())?.toMutableSet() ?: mutableSetOf()
        if (isLiked) {
            favoritePlants.add(plantName)
        } else {
            favoritePlants.remove(plantName)
        }
        sharedPreferences.edit().putStringSet("favorite_plants", favoritePlants).apply()
    }
}
