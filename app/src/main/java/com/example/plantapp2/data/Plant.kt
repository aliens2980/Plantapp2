package com.example.plantapp2.data

import kotlinx.serialization.Serializable

@Serializable
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
        "${nameLatin.first()}",
        "$name ${sun.toString()}",
        "$name ${water.toString()}",
        "$name $gradeText",
        "$nameLatin ${sun.toString()}",
        "$nameLatin ${water.toString()}",
        "$nameLatin $gradeText",
        "$name $nameLatin",
        "$name$nameLatin",
        "$name${sun.toString()}",
        "$name${water.toString()}",
        "$name$gradeText",
        "$nameLatin${sun.toString()}",
        "$nameLatin${water.toString()}",
        "$nameLatin$gradeText"
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
/*
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
 */


