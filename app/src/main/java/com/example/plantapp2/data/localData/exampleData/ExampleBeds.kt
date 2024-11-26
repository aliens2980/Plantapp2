package com.example.plantapp2.data.localData.exampleData

import com.example.plantapp2.data.localData.CellPlant
import com.example.plantapp2.data.localData.LocalBeds

fun generateSampleBeds(): List<LocalBeds> {
    return listOf(
        LocalBeds(id = 1, name = "example 1", length = 200, width = 300),
        LocalBeds(id = 2, name = "example 2", length = 100, width = 50),
        LocalBeds(id = 3, name = "example 3", length = 240, width = 360),
    )
}
fun plantsInBed(bed: LocalBeds): List<CellPlant> {
    return emptyList()
}