package com.example.plantapp2.data.localData

data class LocalBeds(
    val id: Int,
    val name: String,
    val length: Int, // Number of rows
    val width: Int, // Number of columns
    val plantsInBed: MutableList<CellPlant> = mutableListOf() // Mutable for dynamic updates
)

data class CellPlant(
    val localPlant: LocalPlant,
    val placeInBed: PlaceInBed
)

data class PlaceInBed(
    val row: Int,
    val column: Int
)