package com.example.plantapp2.data.localData

import kotlinx.serialization.Serializable


@Serializable
data class LocalBeds(
    val id: Int,
    val name: String,
    val length: Int,
    val width: Int,
    val selectedCells: List<Pair<Int, Int>>,
    val plants: Map<LocalPlant, PlaceInBed> // Map of plants and their positions
)


@Serializable
data class PlaceInBed(
    val row: Int,
    val column: Int
)

val plants: Map<String, PlaceInBed>
    get() {
        TODO()
    }

@Serializable
data class CellPlant(
    val localPlant: LocalPlant,
    val placeInBed: PlaceInBed
)