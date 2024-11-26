package com.example.plantapp2.ui.settings

import androidx.lifecycle.ViewModel
import com.example.plantapp2.data.localData.LocalBeds
import com.example.plantapp2.data.localData.SettingsProfile
import com.example.plantapp2.data.localData.exampleData.generateSampleBeds
import com.example.plantapp2.data.localData.exampleData.startProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class SettingsViewModel : ViewModel() {
    private val _settingsProfile = MutableStateFlow(startProfile())
    val settingsProfile: StateFlow<SettingsProfile> = _settingsProfile

    private val _beds = MutableStateFlow(generateSampleBeds())
    open val beds: StateFlow<List<LocalBeds>> = _beds


    fun updateProfile(newName: String, newEmail: String) {
        _settingsProfile.value = _settingsProfile.value.copy(
            name = newName,
            email = newEmail
        )
    }
}

