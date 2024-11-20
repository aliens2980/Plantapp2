package com.example.plantapp2.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SaveBed() {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        content = { innerPadding ->
            Column {
                Text(
                    text = "Name and save bed",
                    modifier = Modifier
                        .padding(innerPadding)
                        .align(Alignment.CenterHorizontally),
                )
                Button(
                    onClick = { /* Handle button click */ },
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(Color.Green),
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Save",
                        color = Color.Black
                    )

                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SaveBedPreview() {
    SaveBed()
}