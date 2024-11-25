package com.example.plantapp2.plants

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.plantapp2.data.Plant
//import com.example.plantapp2.data.FirebaseCallback
import com.example.plantapp2.plants.PlantsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.Dispatchers

class PlantsViewModel (
    private val repository: PlantsRepository = PlantsRepository()
): ViewModel() {
    /*
    fun getResponseUsingCallback(callback: FirebaseCallback) = repository.getResponseFromFirestoreUsingCallback(callback)
*/
    fun getResponseUsingLiveData() = repository.getResponseFromFirestoreUsingLiveData()

    suspend fun getPlantDetailsByName(name: String): Plant? {
        return repository.getPlantByName(name)
    }

    /*
    val responseLiveData = liveData(Dispatchers.IO) {
        emit(repository.getResponseFromFirestoreUsingCoroutines())
    }

    fun getResponseUsingFlow() = liveData(Dispatchers.IO) {
        repository.getResponseFromFirestoreUsingFlow().collect { response ->
            emit(response)
        }
    }
     */
}