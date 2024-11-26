package com.example.plantapp2.ui.components.bedChooser

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.plantapp2.Data.generateSampleBeds

@Composable
fun ChooseBedScreen() {
    val _bedList = generateSampleBeds()
    val bedList = _bedList.toMutableList()
    //List of available beds

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier.fillMaxSize()
    ) {
        items(bedList.size) { index ->
            BedCard(bed = bedList[index])
        }
    }
}