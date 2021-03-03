package com.carlosjimz87.stopwatch.domain.timer

import androidx.lifecycle.MutableLiveData
import com.carlosjimz87.stopwatch.domain.timer.Timer
import com.carlosjimz87.stopwatch.domain.timer.TimerImpl
import com.carlosjimz87.stopwatch.ui.watch.WatchViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class TimerTest {
    private var  formattedText:String = "00:00:00"
    private var state: Timer.STATES = Timer.STATES.START
    private lateinit var viewModel: WatchViewModel

    @Before
    fun init(){
        viewModel = WatchViewModel()
    }

    @Test
    @Throws(Exception::class)
    fun test_timer_started() {

//        val mockFormattedText = mockk<MutableLiveData<String>>()
//        every {
//            mockFormattedText.value
//        }returns formattedText
//
//        val mockState = mockk<MutableLiveData<Timer.STATES>>()
//        every {
//            mockState.value
//        }returns state
//
//
//        val timer: Timer = TimerImpl(mockFormattedText)
//
//        timer.startTimer(mockState)
//
//        //VERIFY
//        assertNotEquals(formattedText,"00:00:00")
//        assertEquals(state,Timer.STATES.PAUSE)

    }
}