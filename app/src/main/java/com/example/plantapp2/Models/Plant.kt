package com.example.plantapp2.Models

data class Plant(
    val name: String = "",
    val isFavorite: Boolean = false
) {
    val image: Int
        get() {
            TODO()
        }
}
