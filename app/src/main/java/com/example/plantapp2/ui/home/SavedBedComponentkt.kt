package com.example.plantapp2.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.example.plantapp2.R
import com.example.plantapp2.data.localData.LocalBeds

@Composable
fun SavedBedScreen(
    length: Int,
    width: Int,
    cellState: List<Pair<Int, Int>>
) {
    val rows = (length / 20).coerceAtLeast(1)
    val cols = (width / 20).coerceAtLeast(1)

    val selectedCells = cellState.toSet()

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (row in 0 until rows) {
            Row(horizontalArrangement = Arrangement.Center) {
                for (col in 0 until cols) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(Color.Transparent)
                    ) {
                        if (selectedCells.contains(row to col)) {
                            Image(
                                painter = painterResource(id = R.drawable.dirt),
                                contentDescription = "Dirt tile",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}




@Composable
fun SavedBedItem(
    bed: LocalBeds,
    onBedClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ClickableText(
            text = AnnotatedString(bed.name),
            onClick = { onBedClick() },
            modifier = Modifier.weight(1f)
        )

        IconButton(onClick = { onDeleteClick() }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Bed",
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}

