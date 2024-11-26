package com.example.plantapp2.ui.settings.addBed

import androidx.lifecycle.ViewModel
import com.example.plantapp2.data.localData.LocalBeds
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

    fun saveBed() {
        val name = _bedName.value
        val length = _bedLength.value
        val width = _bedWidth.value

        if (name.isNotBlank() && length > 0 && width > 0) {
            _newBed = LocalBeds(
                id = 0, // Replace with unique ID generator
                name = name,
                length = length,
                width = width,
                plantsInBed = mutableListOf()
            )
            println("Bed saved: $name, Length: $length, Width: $width")
        } else {
            println("Failed to save bed. Please check your inputs.")
        }
    }
}
