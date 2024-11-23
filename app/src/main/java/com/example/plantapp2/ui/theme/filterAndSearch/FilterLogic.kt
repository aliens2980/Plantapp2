package com.example.plantapp2.ui.theme.filterAndSearch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class FilterLogic {
    val plants =
        mutableStateListOf(
            Plants("Kartofler", "Grøntsag", "Brun", 10.0, 20.0, true, "\uD83E\uDD54"),
            Plants("Jordbær", "Nød", "Rød", 25.0, 10.0, false,"\uD83C\uDF53"),
            Plants("Brændnæller", "Urt", "Grøn", 30.0, 30.0, true,"\uD83C\uDF3F"),
            Plants("Gulerødder", "Grøntsag", "Orange", 10.0, 40.0, false,"\uD83E\uDD55"),
            Plants("Oliven", "Frugt", "Grøn", 100.0, 40.0, true,"\uD83E\uDED2"),
            Plants("Tomato", "Grøntsag", "Rød", 80.0, 50.0, true,"\uD83C\uDF45"),
            Plants("Agurk", "Grøntsag", "Grøn", 25.0, 30.0, false,"\uD83E\uDD52")
        )

    var filterFavorite by mutableStateOf(false)
    var filterType by mutableStateOf("")
    var filterColor by mutableStateOf("")
    var filtermaxHeight by mutableStateOf<Double?>(null)
    var filterminHeight by mutableStateOf<Double?>(null)
    var filterMaxRootNet by mutableStateOf<Double?>(null)
    var filterminMaxRootNet by mutableStateOf<Double?>(null)


    val sortedPlants = plants.filter { plant ->
                (!filterFavorite || plant.favorite) &&
                (filterType.isEmpty() || plant.type == filterType) &&
                (filterColor.isEmpty() || plant.color == filterColor) &&
                (filterminHeight == null || (filterminHeight!! > 0 && plant.height <= filterminHeight!!)) &&
                (filtermaxHeight == null || (filtermaxHeight!! > 0 && plant.height >= filtermaxHeight!!)) &&
                (filterMaxRootNet == null || (filterMaxRootNet!! > 0 && plant.maxRootNet >= filterMaxRootNet!!)) &&
                (filterminMaxRootNet == null || (filterminMaxRootNet!! > 0 && plant.maxRootNet <= filterminMaxRootNet!!))
    }

    fun sortFavorite(){
        filterFavorite
    }
}
