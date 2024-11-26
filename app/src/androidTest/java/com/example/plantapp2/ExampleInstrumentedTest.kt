package com.example.plantapp2

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.plantapp2.ui.theme.bed.Bed

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.plantapp2", appContext.packageName)
    }

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun bedGridRendersAndCellIsClickable() {
        // Set the content to display the Bed composable
        composeTestRule.setContent {
            Bed(length = 240, width = 360)
        }

        // Check if the grid is displayed
        composeTestRule.onNodeWithTag("Grid")
            .assertIsDisplayed()

        // Check if a specific cell can be clicked and toggled
        composeTestRule.onNodeWithTag("Cell-0-0")
            .assertIsDisplayed()
            .performClick() // Toggle the cell
            .assertIsSelected() // Verify it’s now selected
    }
}