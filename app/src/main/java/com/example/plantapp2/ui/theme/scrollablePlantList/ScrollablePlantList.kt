package com.example.plantapp2.ui.theme.scrollablePlantList
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
//import com.example.plantapp2.Models.Affirmation
import com.example.plantapp2.plants.PlantItem
import com.example.plantapp2.plants.PlantsViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
/*

@Composable
fun ScrollablePlantList(modifier: Modifier = Modifier, navController: NavController) {

    var affirmations by remember { mutableStateOf<List<Affirmation>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val firestore = FirebaseFirestore.getInstance()

    // Load affirmations from Firestore
    LaunchedEffect(Unit) {
        try {
            val result = firestore.collection("plants").get().await()
            val fetchedAffirmations =
                result.documents.mapNotNull { it.toObject(Affirmation::class.java) }
            affirmations = fetchedAffirmations
        } catch (e: Exception) {
            errorMessage = "Failed to load affirmations: ${e.message}"
        } finally {
            isLoading = false
        }
    } */



    @Composable
    fun ScrollablePlantList(modifier: Modifier = Modifier, navController: NavController, viewModel: PlantsViewModel = viewModel()) {
        val response by viewModel.getResponseUsingLiveData().observeAsState()

        when {
            response == null -> {
                // Show loading state
                Text("Loading...")
            }

            response?.exception != null -> {
                // Show error state
                Text("Error: ${response?.exception?.message}")
            }

            !response?.plants.isNullOrEmpty() -> {
                // Show plants in a list
                LazyColumn {
                    items(response?.plants ?: emptyList()) { plant ->
                        PlantItem(plant)
                    }
                    /*
                    PlantList(
                        plantList = response.plants,
                        navController = navController,
                        modifier = Modifier.fillMaxSize() // Ensure it fills the screen
                    )*/

                }
            }

            else -> {
                // Handle case where the response is valid but the list is empty
                Text("No plants available.")
            }
        }
    }


/*
    // Main Surface
    Surface(
        modifier = modifier
            .fillMaxSize()
            //.background(Color(0xFFDAD7CD)) // Light green background
            .background(Color(0xFFDAD7CD))
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Loading...",
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
            }
        } else if (errorMessage != null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = errorMessage ?: "Unknown error",
                    //style = MaterialTheme.typography.headlineSmall,
                )
            }
        } else {
            // Scrollable list of affirmations
            PlantList(
                affirmationLIST = affirmations,
                navController = navController,
                modifier = Modifier.fillMaxSize() // Ensure it fills the screen
            )
        }
    }
}

*/


/*
// Do: This function is a descriptive PascalCased noun as a visual UI element
@Composable
fun AffirmationsApp(modifier: Modifier = Modifier, navController: NavController) {
    val layoutDirection = LocalLayoutDirection.current
    var affirmations by remember { mutableStateOf<List<Affirmation>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val firestore = FirebaseFirestore.getInstance()

    LaunchedEffect(Unit) {
        try {
            val result = firestore.collection("plants").get().await()
            val fetchedAffirmations =
                result.documents.mapNotNull { it.toObject(Affirmation::class.java) }
            affirmations = fetchedAffirmations
        } catch (e: Exception) {
            errorMessage = "Failed to load affirmations: ${e.message}"
        } finally {
            isLoading = false

        }
    }

    Surface(
        modifier = modifier
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateStartPadding(layoutDirection),
            )
    ) {
        if (isLoading) {
            Text(text = "Loading...")
        } else if (errorMessage != null) {
            Text(text = errorMessage ?: "Unknown error")
        } else {
            AffirmationsList(
                affirmationLIST = affirmations,
                navController = navController
            )
        }
    }
}
@Composable
fun AffirmationsList(affirmationLIST: List<Affirmation>, navController: NavController, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(affirmationLIST) { affirmation ->
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier
                    .padding(4.dp)
                    .clickable {
                        navController.navigate("plantPage")
                    }
            )
        }
    }
}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier){
    Card(modifier = modifier){
        Column {
            AsyncImage(
                model = affirmation.img,
                contentDescription = affirmation.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = affirmation.name,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }

    }
}
*/