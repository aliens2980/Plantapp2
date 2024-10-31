package com.example.plantapp2.ui.theme

import com.example.plantapp2.ui.theme.PlantInfoPage
import org.junit.Test
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.*
import org.junit.Rule
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue






/**
 * @author s235064
 * @param tests of plantPage
 */


class ToggleLikeStateTest {

    /**
     * @author s235064
     * test toggle state when initially false, should return true
     */
    @Test
    fun testToggleLikeState_returnTrue() {
        val initialState = false
        val newState = toggleLikeState(initialState)
        assertTrue(newState)

    }

    @Test
    fun testToggleLikeState_returnFalse() {
        val initialState = true
        val newState = toggleLikeState(initialState)
        assertFalse(newState)
    }

}


