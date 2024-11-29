package com.example.plantapp2.ui.settings

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.plantapp2.data.localData.LocalBeds
import com.example.plantapp2.data.localData.exampleData.startProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.json.Json
import java.io.File

class SettingsViewModel(private val context: Context) : ViewModel() {
    private val _beds = MutableStateFlow<List<LocalBeds>>(emptyList())
    val beds: StateFlow<List<LocalBeds>> = _beds
    private val _settingsProfile = MutableStateFlow(startProfile())

    init {
        loadBedsFromFolder()
    }

    private fun loadBedsFromFolder() {
        val bedsFolder = File(context.filesDir, "beds")
        if (bedsFolder.exists() && bedsFolder.isDirectory) {
            val bedFiles = bedsFolder.listFiles()?.filter { it.extension == "json" } ?: emptyList()
            val loadedBeds = bedFiles.mapNotNull { file ->
                try {
                    val json = file.readText()
                    Json.decodeFromString<LocalBeds>(json)
                } catch (e: Exception) {
                    Log.e("LoadBeds", "Failed to load bed: ${file.name}, error: ${e.message}")
                    null // Handle invalid JSON gracefully
                }
            }
            _beds.value = loadedBeds
        }
    }




    fun deleteBed(bed: LocalBeds) {
        val bedFile = File(context.filesDir, "beds/bed_${bed.id}.json")
        if (bedFile.exists()) {
            val deleted = bedFile.delete() // Delete the bed file
            if (deleted) {
                Log.d("DeleteBed", "Successfully deleted: ${bedFile.path}")
            } else {
                Log.e("DeleteBed", "Failed to delete: ${bedFile.path}")
            }
        } else {
            Log.e("DeleteBed", "File not found: ${bedFile.path}")
        }
        _beds.value = _beds.value.filterNot { it.id == bed.id } // Update the list
    }


    fun updateProfile(newName: String, newEmail: String) {
        _settingsProfile.value = _settingsProfile.value.copy(
            name = newName,
            email = newEmail
        )
    }

    fun getBedFromName(bedName: String): LocalBeds? {
        return _beds.value.find { it.name == bedName }
    }
}




