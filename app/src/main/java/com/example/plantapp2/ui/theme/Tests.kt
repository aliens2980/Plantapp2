package com.example.plantapp2
import com.example.plantapp2.ui.theme.plantPage.toggleLikeState


import org.junit.Test
import org.junit.Assert.*

//gradle kan nok ikke finde ud af hvor vores tests ligger i


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
        val result = toggleLikeState(initialState)
        assertTrue(result)

    }

    @Test
    fun testToggleLikeState_returnFalse() {
        val initialState = true
        val result = toggleLikeState(initialState)
        assertFalse(result)
    }

}
