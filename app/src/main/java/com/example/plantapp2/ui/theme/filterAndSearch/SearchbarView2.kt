package com.example.plantapp2.ui.theme.filterAndSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
/*
class SearchbarView2: ViewModel() {


    private val _searchText = MutableStateFlow("")
    val searchText =_searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _plants = MutableStateFlow(AffirmationLIST)
    val plantss = searchText
        //.debounce(500L)
        //.onEach { _isSearching.update { true } }
        .combine(_plants) {text, plantss ->
            if(text.isBlank()) {
                plantss
            } else {
                plantss.filter {
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
}*/