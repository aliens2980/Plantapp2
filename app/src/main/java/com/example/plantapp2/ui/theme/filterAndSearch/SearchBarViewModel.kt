package com.example.plantapp2.ui.theme.filterAndSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.time.debounce

@OptIn(FlowPreview::class)
class SearchBarViewModel: ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText =_searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _plants = MutableStateFlow(plant)
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
}

data class Plants(
    val name: String,
    val type: String,
    val color: String,
    val height: Double,
    val maxRootNet: Double,
    val favorite: Boolean,
    val emoji: String,
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "${name.first()}",
            "$name $type",
            "$type $color",
            "$name $emoji",
            "$name$emoji"
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

private val plant = listOf(
    Plants(name = "Kartofler", type = "Grøntsag", color = "Brun", height = 10.0, maxRootNet = 20.0, favorite = true, emoji = "\uD83E\uDD54"),
    Plants("Jordbær", "Nød", "Rød", 25.0, 10.0, false,"\uD83C\uDF53"),
    Plants("Brændnæller", "Urt", "Grøn", 30.0, 30.0, true,"\uD83C\uDF3F"),
    Plants("Gulerødder", "Grøntsag", "Orange", 10.0, 40.0, false,"\uD83E\uDD55"),
    Plants("Oliven", "Frugt", "Grøn", 100.0, 40.0, true,"\uD83E\uDED2"),
    Plants("Tomato", "Grøntsag", "Rød", 80.0, 50.0, true,"\uD83C\uDF45"),
    Plants("Agurk", "Grøntsag", "Grøn", 25.0, 30.0, false,"\uD83E\uDD52")
)
