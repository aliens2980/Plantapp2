package com.example.plantapp2.data

data class Plant(
    val gradeText: String = "",
    val img: String = "",
    val info: String = "",
    val name: String = "",
    val nameLatin: String = "",
    val prio: List<String> = emptyList(),
    val sun: Int? = null,
    val water: Int? = null
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
        "${name.first()}",
        "${nameLatin.first()}",
        "$name ${sun.toString()}",
        "$name ${water.toString()}",
        "$name $gradeText"
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

