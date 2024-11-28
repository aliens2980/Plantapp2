package com.example.plantapp2.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.plantapp2.data.localData.LocalBeds
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.json.Json
import java.io.File

class SavedBedsViewModel(private val context: Context, BedId: Int) : ViewModel() {
    private val _beds = MutableStateFlow<List<LocalBeds>>(emptyList())
    val beds: StateFlow<List<LocalBeds>> = _beds
    private var myBedId = BedId
    var specficBed: StateFlow<LocalBeds> = loadSpecific(myBedId)

    init {
        loadBeds()
        loadSpecific(myBedId)
    }

    private fun loadSpecific(myBedId: Int): StateFlow<LocalBeds> {
        val bed: LocalBeds = beds.value.get(myBedId)
        return MutableStateFlow<LocalBeds>(bed)
    }

    private fun loadBeds() {
        val bedsFolder = File(context.filesDir, "beds")
        if (bedsFolder.exists() && bedsFolder.isDirectory) {
            val bedFiles = bedsFolder.listFiles()?.filter { it.extension == "json" } ?: emptyList()
            val loadedBeds = bedFiles.mapNotNull { file ->
                try {
                    val json = file.readText()
                    Json.decodeFromString<LocalBeds>(json)
                } catch (e: Exception) {
                    null
                }
            }
            _beds.value = loadedBeds
        }
    }

    fun deleteBed(bedId: Int) {
        val bedFile = File(context.filesDir, "beds/bed_$bedId.json")
        if (bedFile.exists()) {
            bedFile.delete()
            _beds.value = _beds.value.filter { it.id != bedId }
        }
    }
}