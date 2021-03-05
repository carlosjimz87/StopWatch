package com.carlosjimz87.stopwatch.domain.stopwatch

import androidx.lifecycle.MutableLiveData
import com.carlosjimz87.stopwatch.domain.viewmodels.StopWatchViewModel

interface StopWatch {
    fun startTimer(data:MutableLiveData<StopWatchViewModel.STATES>)
    fun pauseTimer(data:MutableLiveData<StopWatchViewModel.STATES>)
    fun resetTimer(data:MutableLiveData<StopWatchViewModel.STATES>)
}
