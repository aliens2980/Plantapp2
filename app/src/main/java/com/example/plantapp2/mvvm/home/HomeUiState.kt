package com.example.plantapp2.mvvm.home

import com.example.plantapp2.Models.Plant

// All slags data der kan ændrer sig - skal defineres herinde
data class HomeUiState(
    val plants: MutableList<Plant> = mutableListOf(),
    val grid: MutableList<Boolean> = mutableListOf()

)
