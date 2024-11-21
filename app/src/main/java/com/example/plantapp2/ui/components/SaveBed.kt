package com.example.plantapp2.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SaveBed(viewModel: BedCreationViewModel) {
    val bedName by viewModel.bedName.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                TextField(
                    value = bedName,
                    onValueChange = { viewModel.updateBedName(it) },
                    placeholder = { Text("Enter bed name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                Button(
                    onClick = { viewModel.saveBed() },
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(Color.Green),
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text("Save", color = Color.Black)
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SaveBedPreview() {
    SaveBed(viewModel = BedCreationViewModel())
}