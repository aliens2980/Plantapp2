package com.example.plantapp2.ui.settings

import androidx.lifecycle.ViewModel
import com.example.plantapp2.data.localData.LocalBeds
import com.example.plantapp2.data.localData.SettingsProfile
import com.example.plantapp2.data.localData.exampleData.startProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import android.content.Context
import android.util.Log
import com.example.plantapp2.utils.JsonObject.jsonFormat
import kotlinx.serialization.json.Json
import java.io.File


class SettingsViewModel(private val context: Context) : ViewModel() {
    private val _beds = MutableStateFlow<List<LocalBeds>>(emptyList())
    val beds: StateFlow<List<LocalBeds>> = _beds
    private val _settingsProfile = MutableStateFlow(startProfile())
    val settingsProfile: StateFlow<SettingsProfile> = _settingsProfile

    init {
        loadBedsFromFolder()
    }

    private fun loadBedsFromFolder() {
        val bedsFolder = File(context.filesDir, "beds")
        if (bedsFolder.exists() && bedsFolder.isDirectory) {
            val bedFiles = bedsFolder.listFiles()?.filter { it.extension == "json" } ?: emptyList()
            Log.d("LoadBeds", "Found files: ${bedFiles.map { it.name }}")
            val loadedBeds = bedFiles.mapNotNull { file ->
                try {
                    val json = file.readText()
                    jsonFormat.decodeFromString<LocalBeds>(json) // Use shared JsonObject.jsonFormat
                } catch (e: Exception) {
                    Log.e("LoadBeds", "Failed to load bed: ${file.name}, error: ${e.message}")
                    null // Skip the invalid bed
                }
            }
            _beds.value = loadedBeds
        } else {
            Log.d("LoadBeds", "No beds folder found or it's not a directory.")
        }
    }





    fun deleteBed(bed: LocalBeds) {
        val bedFile = File(context.filesDir, "beds/${bed.name}.json")
        if (bedFile.exists()) {
            bedFile.delete() // Delete the bed file
        }
        _beds.value = _beds.value.filterNot { it == bed } // Update the list
    }


    fun updateProfile(newName: String, newEmail: String) {
        _settingsProfile.value = _settingsProfile.value.copy(
            name = newName,
            email = newEmail
        )
    }
}

