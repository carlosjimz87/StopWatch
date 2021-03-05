package com.carlosjimz87.stopwatch.domain.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carlosjimz87.stopwatch.domain.stopwatch.StopWatch
import com.carlosjimz87.stopwatch.domain.stopwatch.StopWatchController
import com.carlosjimz87.stopwatch.utils.Constants.INIT_TIME

class StopWatchViewModel : ViewModel() {


    enum class STATES {
        START,
        PAUSE,
        RESUME,
    }

    private val stopWatch: StopWatch by lazy { StopWatchController(_formattedTime) }

    private var _formattedTime = MutableLiveData(INIT_TIME)
    val formattedTime: LiveData<String>
        get() = _formattedTime

    private var _state = MutableLiveData(STATES.START)
    val state: LiveData<STATES>
        get() = _state


    fun resetTimer() {
        stopWatch.resetTimer(_state)
    }

    fun startPauseTimer(){
        when (_state.value) {
            STATES.START -> stopWatch.startTimer(_state)
            STATES.RESUME -> stopWatch.startTimer(_state)
            STATES.PAUSE -> stopWatch.pauseTimer(_state)
            else -> {
            }
        }
    }



}