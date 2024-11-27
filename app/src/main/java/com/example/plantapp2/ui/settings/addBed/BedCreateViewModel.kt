package com.example.plantapp2.ui.settings.addBed

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.plantapp2.data.localData.LocalBeds
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

    fun saveBed(context: Context, selectedCells: List<Pair<Int, Int>>) {
        val length = bedLength.value
        val width = bedWidth.value
        val name = bedName.value

        if (name.isNotBlank() && selectedCells.isNotEmpty()) {
            val bed = LocalBeds(
                id = System.currentTimeMillis().toInt(),
                name = name,
                length = length,
                width = width,
                selectedCells = selectedCells,
                plants = mapOf() // Placeholder for plant mappings
            )
            saveJsonToFile(context, "bed_${bed.id}.json", bed)
            println("Bed saved locally: $bed")
        } else {
            println("Failed to save bed. Name or cells are invalid.")
        }
    }
}
