package com.example.plantapp2.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.plantapp2.data.Plant
import com.example.plantapp2.plants.PlantsViewModel

@Composable
fun getPlantByName(plantName: String, viewModel: PlantsViewModel = viewModel()
): Plant? {
    val response by viewModel.getResponseUsingLiveData().observeAsState()
    return response?.plants?.find { it.name.equals(plantName, ignoreCase = true) }
}






