package com.carlosjimz87.stopwatch.domain.stopwatch

import androidx.lifecycle.MutableLiveData
import com.carlosjimz87.stopwatch.ui.stopwatch.StopWatchViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class StopWatchTest {
    private var  formattedText = MutableLiveData("00:00:00")
    private var state = MutableLiveData(StopWatch.STATES.START)
    private lateinit var stopWatch:StopWatch

    @Before
    fun setup(){
        stopWatch = StopWatchImpl(formattedText)
    }
    @Test
    @Throws(Exception::class)
    fun test_timer_started() {
        // ACT
        runBlocking {
            stopWatch.startTimer(state)
        }

        //VERIFY
        assertNotEquals(formattedText.value,"00:00:00")
        assertEquals(state.value,StopWatch.STATES.PAUSE)

    }
}