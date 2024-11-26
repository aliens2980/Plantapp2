package com.example.plantapp2.ui.components.createBed.bed

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BedDimensionsViewModel : ViewModel() {
    private val _bedLength = MutableStateFlow(0)
    val bedLength: StateFlow<Int> = _bedLength

    private val _bedWidth = MutableStateFlow(0)
    val bedWidth: StateFlow<Int> = _bedWidth

    private val _cellState = MutableStateFlow<List<MutableList<Boolean>>>(emptyList())
    val cellState: StateFlow<List<MutableList<Boolean>>> = _cellState

    init {
        updateGridState()
    }

    fun updateBedLength(length: Int) {
        _bedLength.value = length
        updateGridState()
    }

    fun updateBedWidth(width: Int) {
        _bedWidth.value = width
        updateGridState()
    }

    private fun updateGridState() {
        val rows = _bedLength.value / 10
        val cols = _bedWidth.value / 10
        _cellState.value = List(rows) { MutableList(cols) { false } }
    }

    fun toggleCell(row: Int, col: Int) {
        val grid = _cellState.value.map { it.toMutableList() }.toMutableList()
        grid[row][col] = !grid[row][col]
        _cellState.value = grid
    }
}

