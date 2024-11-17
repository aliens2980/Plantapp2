package com.example.plantapp2.Models
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class affirmation(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)
