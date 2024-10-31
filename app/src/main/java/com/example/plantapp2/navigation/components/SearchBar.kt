package com.example.plantapp2.navigation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.res.painterResource
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plantapp2.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainSearch() {
    var text by remember {
        mutableStateOf("")
    }
    var active by remember { mutableStateOf(false) }

    data class Plant(
        val name: String,
        val type: String,
        val color: String,
        val height: Double, // in cm
        val maxRootNet: Double, // in cm
        val favorite: Boolean,
    )

    //PlaceHolder items for search bar
    val items = remember {
        mutableStateListOf(
            Plant("Kartofler", "Grøntsag", "Brun", 10.0, 20.0, true),
            Plant("Jordbær", "Nød", "Rød",25.0,10.0, true),
            Plant("Brændnæller", "Urt", "Grøn", 30.0, 30.0, true),
            Plant("Gulerødder", "Grøntsag", "Orange", 10.0, 40.0, true),
            Plant("Oliven", "Frugt", "Grøn", 100.0,40.0, true),
            Plant("Tomato", "Grøntsag", "Rød",80.0,50.0, true),
            Plant("Agurk", "Grøntsag", "Grøn", 25.0, 30.0, true)
        )
    }

    Scaffold {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = text,
            onQueryChange = {text = it},
            onSearch = {
                items.add(Plant(text, "NewType", "Color", 0.0, 0.0, true))
                active = false
            },
            active = active,
            onActiveChange = {active = it},
            placeholder = { Text(text = "Search") },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon") },
            trailingIcon = {
                if (active) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        modifier = Modifier.clickable {
                            if (text.isNotEmpty()) {
                                text = ""
                            } else {
                                active = false
                            }

                        }
                    )
                }
            }
        ) {
            items.forEach { plant ->
                Row(modifier = Modifier.padding(all = 16.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.psychiatry_24px),
                        contentDescription = "plant icon",
                        modifier = Modifier.padding(end = 16.dp)
                    )
                    Column {
                        Text(
                            text = plant.name
                        )
                        Text(text = plant.type)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview (){
    MainSearch()
}