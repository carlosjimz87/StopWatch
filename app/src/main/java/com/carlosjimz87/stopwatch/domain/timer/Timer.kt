package com.carlosjimz87.stopwatch.domain.timer

import androidx.lifecycle.MutableLiveData

interface Timer {

    enum class STATES {
        START,
        PAUSE,
        RESUME,
    }

    fun startTimer(data:MutableLiveData<STATES>)
    fun stopTimer(data:MutableLiveData<STATES>)
    fun resetTimer(data:MutableLiveData<STATES>)
}