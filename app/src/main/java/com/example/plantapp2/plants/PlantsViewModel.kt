package com.example.plantapp2.plants

import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.plantapp2.data.Plant
import com.example.plantapp2.data.Response
//import com.example.plantapp2.data.FirebaseCallback
import com.example.plantapp2.plants.PlantsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

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
    private val _searchText = MutableStateFlow("")
    val searchText =_searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _plants = MutableStateFlow()
    val plants = searchText
        //.debounce(500L)
        //.onEach { _isSearching.update { true } }
        .combine(_plants) {text, plants ->
            if(text.isBlank()) {
                plants
            } else {
                plants?.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        //.onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _plants.value
        )
    fun onSearchTextChange(text: String) {
        _searchText.value = text
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
     */*/
}