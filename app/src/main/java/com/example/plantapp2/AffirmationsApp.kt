package com.example.plantapp2
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.plantapp2.Models.Affirmation
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


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
