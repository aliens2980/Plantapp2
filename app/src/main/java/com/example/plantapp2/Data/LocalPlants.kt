package com.example.plantapp2.Data

data class LocalPlant(
    val name: String,
    val image: Int,
    var isFavorite: Boolean, // Mutable to allow toggling favorite status
    val size: Int, // Represents the size of the plant
    val compatiblePlants: List<String> = emptyList() // List of plant names it works well with
)

data class Bed(
    val id: Int,
    val name: String,
    val length: Int, // Number of rows
    val width: Int, // Number of columns
    val plantsInBed: MutableList<CellPlant> = mutableListOf() // Mutable for dynamic updates
) {
    // Calculate grid size dynamically
    val gridSize: Int
        get() = length * width
}

data class CellPlant(
    val localPlant: LocalPlant,
 