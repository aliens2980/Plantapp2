package com.example.plantapp2.ui.settings.addBed.customizeBed

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.pow
import kotlin.math.sqrt

class BedDimensionsViewModel : ViewModel() {
    private val _bedLength = MutableStateFlow(200)
    val bedLength: StateFlow<Int> = _bedLength

    private val _bedWidth = MutableStateFlow(250)
    val bedWidth: StateFlow<Int> = _bedWidth

    private val _cellState = MutableStateFlow<List<MutableList<Boolean>>>(emptyList())
    val cellState: StateFlow<List<MutableList<Boolean>>> = _cellState

    init {
        updateGridState()
        applyLShapeFill() // Default to L Shape
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
        val rows = (_bedLength.value / 20).coerceAtLeast(1)
        val cols = (_bedWidth.value / 20).coerceAtLeast(1)
        _cellState.value = List(rows) { MutableList(cols) { false } }
    }

    fun toggleCell(row: Int, col: Int) {
        val grid = _cellState.value.map { it.toMutableList() }.toMutableList()
        grid[row][col] = !grid[row][col]
        _cellState.value = grid
    }

    fun getSelectedCells(): List<Pair<Int, Int>> {
        return cellState.value.flatMapIndexed { rowIndex, row ->
            row.mapIndexedNotNull { colIndex, isSelected ->
                if (isSelected) rowIndex to colIndex else null
            }
        }
    }


    fun applySelectAll() {
        _cellState.value = _cellState.value.map { row -> row.map { true }.toMutableList() }
    }

    fun applyCircleFill() {
        val rows = _cellState.value.size
        val cols = _cellState.value.firstOrNull()?.size ?: 0
        val centerX = rows / 2
        val centerY = cols / 2
        val radius = minOf(rows, cols) / 2

        _cellState.value = List(rows) { row ->
            MutableList(cols) { col ->
                val distance = sqrt(((row - centerX).toDouble().pow(2)) + ((col - centerY).toDouble().pow(2)))
                distance <= radius
            }
        }
    }

    fun applyLShapeFill() {
        val rows = _cellState.value.size
        val cols = _cellState.value.firstOrNull()?.size ?: 0
        val halfRows = (rows / 2).coerceAtLeast(1) // Top half of rows
        val halfCols = (cols / 2).coerceAtLeast(1) // Left half of columns

        _cellState.value = List(rows) { row ->
            MutableList(cols) { col ->
                row < halfRows || col < halfCols // Fill top half rows or left half columns
            }
        }
    }
}

