package com.example.plantapp2.Model
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class affirmation(
    @StringRes val stringResourceId: Int,
    val imageUrl: String
)
