package com.carlosjimz87.stopwatch.domain.stopwatch

import androidx.lifecycle.MutableLiveData

interface StopWatch {

    enum class STATES {
        START,
        PAUSE,
        RESUME,
    }

    fun startTimer(data:MutableLiveData<STATES>)
    fun stopTimer(data:MutableLiveData<STATES>)
    fun resetTimer(data:MutableLiveData<STATES>)
}