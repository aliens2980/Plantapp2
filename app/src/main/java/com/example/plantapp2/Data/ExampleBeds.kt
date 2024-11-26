package com.example.plantapp2.Data

import com.example.plantapp2.R

fun generateSampleBeds(): List<Bed> {
    return listOf(
        Bed(id = 1, name = "example 1", length = 200, width = 300),
        Bed(id = 2, name = "example 2", length = 100, width = 50),
        Bed(id = 3, name = "example 3", length = 240, width = 360),
    )
}
fun plantsInBed(bed: Bed): List<CellPlant> {
    return emptyList()
}