package com.carlosjimz87.stopwatch.ui.stopwatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carlosjimz87.stopwatch.domain.stopwatch.StopWatch
import com.carlosjimz87.stopwatch.domain.stopwatch.StopWatch.STATES
import com.carlosjimz87.stopwatch.domain.stopwatch.StopWatchImpl
import com.carlosjimz87.stopwatch.utils.Constants.INIT_TIME

class StopWatchViewModel : ViewModel() {
    private val stopWatch: StopWatch by lazy { StopWatchImpl(_formattedTime) }



    private var _formattedTime = MutableLiveData(INIT_TIME)
    var formattedTime: LiveData<String>
        get() = _formattedTime
        set(value) { _formattedTime.value = value.value  }

    private var _startStopButtonState = MutableLiveData(STATES.START)
    var startStopButtonState: LiveData<STATES>
        get() = _startStopButtonState
        set(value) { _startStopButtonState.value = value.value  }

    fun startTimer() = stopWatch.startTimer(_startStopButtonState)
    fun resetTimer() = stopWatch.resetTimer(_startStopButtonState)
    fun stopTimer() = stopWatch.stopTimer(_startStopButtonState)


}