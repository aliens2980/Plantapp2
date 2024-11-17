package com.example.plantapp2
import androidx.compose.foundation.Image
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.plantapp2.Data.Datasource
import com.example.plantapp2.Models.affirmation


// Do: This function is a descriptive PascalCased noun as a visual UI element
@Composable
fun AffirmationsApp(modifier: Modifier = Modifier, navController: NavController) {
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = modifier
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues().calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues().calculateStartPadding(layoutDirection),
            )
    ) {
        AffirmationsList(
            affirmationLIST = Datasource().loadAffirmations(),
            navController = navController
        )
    }
}
@Composable
fun AffirmationsList(affirmationLIST: List<affirmation>, navController: NavController, modifier: Modifier = Modifier) {
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
fun AffirmationCard(affirmation: affirmation, modifier: Modifier = Modifier){
    Card(modifier = modifier){
        Column {
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = LocalContext.current.getString(affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }

    }
}
