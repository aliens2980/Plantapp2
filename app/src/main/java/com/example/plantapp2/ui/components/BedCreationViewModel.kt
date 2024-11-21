package com.example.plantapp2.ui.components

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BedCreationViewModel : ViewModel() {
    private val _bedLength = MutableStateFlow("")
    val bedLength: StateFlow<String> = _bedLength

    private val _bedWidth = MutableStateFlow("")
    val bedWidth: StateFlow<String> = _bedWidth

    private val _bedName = MutableStateFlow("")
    val bedName: StateFlow<String> = _bedName

    // Functions to update state
    fun updateBedLength(length: String) {
        if (length.all { it.isDigit() }) {
            _bedLength.value = length
        }
    }

    fun updateBedWidth(width: String) {
        if (width.all { it.isDigit() }) {
            _bedWidth.value = width
        }
    }

    fun updateBedName(name: String) {
        _bedName.value = name
    }

    fun saveBed() {
        // Save bed logic, e.g., send to a database or API
        println("Bed saved: Length=${_bedLength.value}, Width=${_bedWidth.value}, Name=${_bedName.value}")
    }
}
