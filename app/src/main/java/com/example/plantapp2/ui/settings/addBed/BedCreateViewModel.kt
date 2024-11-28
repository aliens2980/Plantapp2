package com.example.plantapp2.ui.settings.addBed

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.plantapp2.data.localData.LocalBeds
import com.example.plantapp2.data.localData.PlantInBed
import com.example.plantapp2.utils.saveJsonToFile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class BedCreationViewModel : ViewModel() {
    private val _bedLength = MutableStateFlow(240)
    val bedLength: StateFlow<Int> = _bedLength

    private val _bedWidth = MutableStateFlow(360)
    val bedWidth: StateFlow<Int> = _bedWidth

    private val _bedName = MutableStateFlow("")
    val bedName: StateFlow<String> = _bedName

    private var _newBed: LocalBeds? = null
    val newBed: LocalBeds?
        get() = _newBed

    fun updateBedLength(length: Int) {
        _bedLength.value = length
    }

    fun updateBedWidth(width: Int) {
        _bedWidth.value = width
    }

    fun updateBedName(name: String) {
        _bedName.value = name
    }

    fun saveBed(context: Context, selectedCells: List<Pair<Int, Int>>, plantsInBed: List<PlantInBed> = emptyList()) {
        val bed = LocalBeds(
            id = System.currentTimeMillis().toInt(),
            name = bedName.value,
            length = bedLength.value,
            width = bedWidth.value,
            selectedCells = selectedCells,
            plants = plantsInBed // Pass the list of plants in the bed
        )

        try {
            saveJsonToFile(context, "bed_${bed.id}.json", bed)
            Log.d("BedCreation", "Bed saved successfully: $bed")
        } catch (e: Exception) {
            Log.e("BedCreation", "Error saving bed: ${e.message}", e)
        }
    }

}
