package com.carlosjimz87.stopwatch.ui.watch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carlosjimz87.stopwatch.domain.timer.Timer
import com.carlosjimz87.stopwatch.domain.timer.Timer.STATES
import com.carlosjimz87.stopwatch.domain.timer.TimerImpl
import com.carlosjimz87.stopwatch.utils.Constants.INIT_TIME

class WatchViewModel : ViewModel() {
    private val timer: Timer by lazy { TimerImpl(_formattedTime) }


    private var _formattedTime = MutableLiveData(INIT_TIME)
    var formattedTime: LiveData<String>
        get() = _formattedTime
        set(value) { _formattedTime.value = value.value  }

    private var _startStopButtonState = MutableLiveData(STATES.START)
    var startStopButtonState: LiveData<STATES>
        get() = _startStopButtonState
        set(value) { _startStopButtonState.value = value.value  }

    fun startTimer() = timer.startTimer(_startStopButtonState)
    fun resetTimer() = timer.resetTimer(_startStopButtonState)
    fun stopTimer() = timer.stopTimer(_startStopButtonState)


}