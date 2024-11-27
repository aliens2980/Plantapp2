package com.example.plantapp2.data.localData

import kotlinx.serialization.Serializable

@Serializable
data class LocalBeds(
    val id: Int,
    val name: String,
    val length: Int,
    val width: Int,
    val selectedCells: List<Pair<Int, Int>>, // List of selected cells
    val plants: Map<Pair<Int, Int>, String> // Map of cell coordinates to plant IDs
)

data class CellPlant(
    val localPlant: LocalPlant,
    val placeInBed: PlaceInBed
)

data class PlaceInBed(
    val row: Int,
    val column: Int
)