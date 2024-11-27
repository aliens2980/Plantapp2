package com.example.plantapp2.data


data class Plant(
    val depth: Int? = null,
    val gradeImg: String = "",
    val gradeText: String = "",
    val img: String = "",
    val info: String = "",
    val informationImg: String = "",
    val name: String = "",
    val nameLatin: String = "",
    val prio: List<String> = emptyList(),
    val sun: Int? = null,
    val water: Int? = null,
    val isLiked: Boolean = false // Add this field if it doesn’t already exist
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "${name.first()}",
            "$name ${depth.toString()}",
            "${nameLatin.first()}",
            "$name $prio",
            "$name ${sun.toString()}"
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

