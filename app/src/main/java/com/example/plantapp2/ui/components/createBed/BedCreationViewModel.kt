package com.example.plantapp2.ui.components.createBed

import androidx.lifecycle.ViewModel
import com.example.plantapp2.Data.Bed
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BedCreationViewModel : ViewModel() {
    private val _bedLength = MutableStateFlow(240)
    val bedLength: StateFlow<Int> = _bedLength

    private val _bedWidth = MutableStateFlow(360)
    val bedWidth: StateFlow<Int> = _bedWidth

    private val _bedName = MutableStateFlow("")
    val bedName: StateFlow<String> = _bedName

    private val _lengthInput = MutableStateFlow("240")
    val lengthInput: StateFlow<String> = _lengthInput

    private val _widthInput = MutableStateFlow("360")
    val widthInput: StateFlow<String> = _widthInput

    private var _newBed: Bed? = null
    val newBed: Bed?
        get() = _newBed

    // Functions to update state
    fun updateLengthInput(length: String) {
        _lengthInput.value = length
        if (length.all { it.isDigit() }) {
            _bedLength.value = length.toInt()
        }
    }

    fun updateWidthInput(width: String) {
        _widthInput.value = width
        if (width.all { it.isDigit() }) {
            _bedWidth.value = width.toInt()
        }
    }

    fun updateBedName(name: String) {
        _bedName.value = name
    }

    fun saveBed() {
        // Ensure valid inputs before creating the Bed object
        val length = _lengthInput.value.toIntOrNull()
        val width = _widthInput.value.toIntOrNull()
        val name = _bedName.value

        if (length != null && width != null && name.isNotBlank()) {
            _newBed = Bed(
                id = 0, // Assign a unique ID in future to be able to create more than one
                name = name,
                length = length,
                width = width
            )
            println("Bed saved: Length=$length, Width=$width, Name=$name")
        } else {
            println("Failed to save bed. Please check your inputs.")
        }
    }
}

