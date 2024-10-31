package com.example.plantapp2.Data

import com.example.plantapp2.Model.affirmation
import com.example.plantapp2.R


class Datasource() {
    fun loadAffirmations(): List<affirmation> {
        return listOf<affirmation>(
            affirmation(R.string.potatoes, "https://cdn.britannica.com/08/194708-050-56FF816A/potatoes.jpg"),
            affirmation(R.string.plantapp, "https://cdn.britannica.com/08/194708-050-56FF816A/potatoes.jpg"),
            affirmation(R.string.plantapp, "https://cdn.britannica.com/08/194708-050-56FF816A/potatoes.jpg"),
            affirmation(R.string.hello_world, "https://cdn.britannica.com/08/194708-050-56FF816A/potatoes.jpg"),
            affirmation(R.string.hello_world, "https://cdn.britannica.com/08/194708-050-56FF816A/potatoes.jpg"),
            affirmation(R.string.hello_world, "https://cdn.britannica.com/08/194708-050-56FF816A/potatoes.jpg"),
            affirmation(R.string.hello_world, "https://cdn.britannica.com/08/194708-050-56FF816A/potatoes.jpg"),
            affirmation(R.string.hello_world, "https://cdn.britannica.com/08/194708-050-56FF816A/potatoes.jpg"),
            affirmation(R.string.hello_world, "https://cdn.britannica.com/08/194708-050-56FF816A/potatoes.jpg"),
            affirmation(R.string.hello_world, "https://cdn.britannica.com/08/194708-050-56FF816A/potatoes.jpg"),
        )
    }
}