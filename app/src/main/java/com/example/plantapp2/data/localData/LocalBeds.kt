package com.example.plantapp2.data.localData

import kotlinx.serialization.Serializable


@Serializable
data class LocalBeds(
    val id: Int,
    val name: String,
    val length: Int,
    val width: Int,
    val selectedCells: List<Pair<Int, Int>>, // Selected cells in the bed
    val plants: List<PlantInBed> = emptyList() // Plants in the bed
)



@Serializable
data class PlantInBed(
    val name: String,
    val position: Pair<Int, Int> // Row and column in the bed
)


