package com.example.plantapp2.ui.components.addToBed

/*
@Composable
fun FavoritePlantsScroll(allPlants: List<LocalPlant>) {
    val shownPlants =
    if (favoritePlants.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Find your favorite plants in the plant library! When a plant is added as a favorite, it will appear here. Then you can start adding plants to the bed.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
            Button(onClick = { /* Navigate to plant library */ }) {
                Text("Go to plant library")
            }
        }
    } else {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(favoritePlants) { plant ->
                FavoritePlantCard(plant)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FavoritePlantScrollViewPreview() {
    FavoritePlantsScroll(generateSamplePlants())
}
*/