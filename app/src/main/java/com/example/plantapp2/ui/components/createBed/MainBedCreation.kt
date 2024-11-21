package com.example.plantapp2.ui.components.createBed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainBedCreationScreen(viewModel: BedCreationViewModel = viewModel()) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize() // Ensure the Column takes the full size of the screen
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween // Distribute components vertically
            ) {
                // BedCreationInfo at the top
                BedCreationInfo(viewModel = viewModel)

            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun MainBedCreationScreenPreview() {
    val mockViewModel = BedCreationViewModel(/* provide any required mocked dependencies here */)
    MainBedCreationScreen(viewModel = mockViewModel)
}
