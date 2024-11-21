package com.example.plantapp2.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
            Column(modifier = Modifier.padding(innerPadding)) {
                BedCreationInfo(viewModel = viewModel)
                Spacer(modifier = Modifier.height(16.dp))
                SaveBed(viewModel = viewModel)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainBedCreationScreenPreview() {
    MainBedCreationScreen()
}
