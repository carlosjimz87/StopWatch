package com.carlosjimz87.stopwatch.domain.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.domain.stopwatch.StopWatchController
import com.carlosjimz87.stopwatch.utils.Constants.INIT_TIME

class StopWatchViewModel : ViewModel() {


    enum class STATES {
        START,
        PAUSE,
        RESUME,
    }


    private var _saveRecord = MutableLiveData(Record("","",""))
    val saveRecord: LiveData<Record>
        get() = _saveRecord


    private var _formattedTime = MutableLiveData(INIT_TIME)
    val formattedTime: LiveData<String>
        get() = _formattedTime

    private var _state = MutableLiveData(STATES.START)
    val state: LiveData<STATES>
        get() = _state

    private val stopWatch by lazy { StopWatchController(_formattedTime,_state, _saveRecord) }

    fun resetTimer() {
        stopWatch.resetTimer()
    }

    fun startPauseTimer(){
        when (_state.value) {
            STATES.START -> stopWatch.startTimer()
            STATES.RESUME -> stopWatch.startTimer()
            STATES.PAUSE -> stopWatch.pauseTimer()
            else -> {
            }
        }
    }



}