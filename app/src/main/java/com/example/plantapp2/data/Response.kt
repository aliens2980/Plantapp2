package com.example.plantapp2.data

data class Response(
    var plants: List<Plant>? = null,
    var exception: Exception? = null
)

