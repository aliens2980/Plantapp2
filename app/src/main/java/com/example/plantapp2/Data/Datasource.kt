package com.example.plantapp2.Data

import com.example.plantapp2.Models.affirmation
import com.example.plantapp2.R


class Datasource() {
    fun loadAffirmations(): List<affirmation> {
        return listOf<affirmation>(
            affirmation(R.string.hello_world, R.drawable.horde_background),
            affirmation(R.string.plantapp, R.drawable.horde_background),
            affirmation(R.string.plantapp, R.drawable.horde_background),
            affirmation(R.string.hello_world, R.drawable.horde_background),
            affirmation(R.string.hello_world, R.drawable.horde_background),
            affirmation(R.string.hello_world, R.drawable.horde_background),
            affirmation(R.string.hello_world, R.drawable.horde_background),
            affirmation(R.string.hello_world, R.drawable.horde_background),
            affirmation(R.string.hello_world, R.drawable.horde_background),
            affirmation(R.string.hello_world, R.drawable.horde_background)
        )
    }
}