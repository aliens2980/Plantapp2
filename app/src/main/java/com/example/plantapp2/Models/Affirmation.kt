package com.example.plantapp2.Models
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(
    val depth: Int? = null,
    val gradeImg: String = "",
    val gradeText: String = "",
    val img: String = "",
    val info: String = "",
    val informationImg: String = "",
    val name: String = "",
    val nameLatin: String = "",
    val prio: String = "",
    val sun: Int? = null,
    val water: Int? = null
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
