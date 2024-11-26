package com.example.plantapp2.data.localData

data class LocalPlant(
    val name: String,
    val image: Int,
    var isFavorite: Boolean, // Mutable to allow toggling favorite status
    val size: Int, // Represents the size of the plant
    val compatiblePlants: List<String> = emptyList() // List of plant names it works well with
)

