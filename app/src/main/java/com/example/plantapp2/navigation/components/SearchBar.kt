package com.example.plantapp2.navigation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plantapp2.R

/*
@OptIn(ExperimentalMaterial3Api::class)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainSearch() {
    var text by remember {
        mutableStateOf("")
    }
    var active by remember { mutableStateOf(false) }

    //PlaceHolder items for search bar
    var items = remember {
        mutableStateListOf(
            "Kartofler",
            "Jordbær",
            "Brændnæller",
            "Gulerødder",
            "Oliven",
            "Tomat",
            "Agurk"
        )
    }

    Scaffold {
        val onActiveChange = {active = it}
        val colors1 = SearchBarDefaults.colors()
        SearchBar(
            inputField = {
                SearchBarDefaults.InputField(
                    query = text,
                    onQueryChange = {text = it},
                    onSearch = {
                        items.add(text)
                        active = false
                    },
                    expanded = active,
                    onExpandedChange = onActiveChange,
                    enabled = true,
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
                    },
                    colors = colors1.inputFieldColors,
                    interactionSource = null,
                )
            },
            expanded = active,
            onExpandedChange = onActiveChange,
            modifier = Modifier.fillMaxWidth(),
            shape = SearchBarDefaults.inputFieldShape,
            colors = colors1,
            tonalElevation = SearchBarDefaults.TonalElevation,
            shadowElevation = SearchBarDefaults.ShadowElevation,
            windowInsets = SearchBarDefaults.windowInsets,
            content = fun ColumnScope.() {
                items.forEach {
                    Row(modifier = Modifier.padding(all = 16.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.psychiatry_24px),
                            contentDescription = "plant icon",
                            modifier = Modifier.padding(end = 16.dp)
                        )
                        Text(
                            modifier = Modifier.align(alignment = Alignment.CenterVertically),
                            text = it
                        )

                    }
                }
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview (){
    MainSearch()
}

 */