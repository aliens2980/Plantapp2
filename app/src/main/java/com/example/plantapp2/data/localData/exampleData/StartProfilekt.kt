package com.example.plantapp2.data.localData.exampleData

import com.example.plantapp2.data.localData.SettingsProfile

fun startProfile(): SettingsProfile {
    val name = "John Doe"
    val email = "john.doe@example.com"
    return SettingsProfile(name, email)
}