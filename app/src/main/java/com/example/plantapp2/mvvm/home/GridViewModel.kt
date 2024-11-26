package com.example.plantapp2.mvvm.home

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf

class GridViewModel : ViewModel() {
    // A 2D List representing the clicked state of each cell
    val gridState = mutableStateListOf<MutableList<Boolean>>()

    // Initialize the grid based on the size
    fun initializeGrid(rows: Int, columns: Int) {
        // If gridState is empty, initialize it with all 'false' (not clicked)
        if (gridState.isEmpty()) {
            for (i in 0 until rows) {
                val row = mutableStateListOf<Boolean>()
                for (j in 0 until columns) {
                    row.add(false) // Initially, no cell is clicked
                }
                gridState.add(row)
            }
        }
    }

    // Toggle the clicked state of a cell
    fun toggleCell(row: Int, column: Int) {
        gridState[row][column] = !gridState[row][column]
    }
}
