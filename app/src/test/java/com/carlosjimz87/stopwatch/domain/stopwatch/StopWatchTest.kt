package com.carlosjimz87.stopwatch.domain.stopwatch

import androidx.lifecycle.MutableLiveData
import com.carlosjimz87.stopwatch.ui.stopwatch.StopWatchViewModel
import io.mockk.clearAllMocks
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class StopWatchTest {
    private val  formattedText = MutableLiveData("00:00:00")
    private val state = MutableLiveData(StopWatch.STATES.START)
    private val stopWatch:StopWatch = mockk()

    @Before
    fun setup(){
        clearMocks(formattedText, state, stopWatch)
    }

    @Test
    fun test_timer_started() {
        // SET
        every{
            stopWatch.startTimer(state)
        }returns

        // ACT
        runBlocking {
            stopWatch.startTimer(state)
            stopWatch.stopTimer(state)
        }

        //VERIFY
        assertNotEquals(formattedText.value,"00:00:00")
        assertEquals(state.value,StopWatch.STATES.PAUSE)

    }
}