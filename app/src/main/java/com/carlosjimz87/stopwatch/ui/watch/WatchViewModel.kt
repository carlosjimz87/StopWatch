package com.carlosjimz87.stopwatch.ui.watch

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carlosjimz87.stopwatch.utils.Constants
import com.carlosjimz87.stopwatch.utils.Formatter

class WatchViewModel : ViewModel() {
    enum class STATES {
        START,
        PAUSE,
        RESUME,
    }

    private val mInterval = Constants.TIMER_INTERVAL
    private var mHandler: Handler? = null
    private var milliseconds = 0L

    private var _formattedTime = MutableLiveData("")
    val formattedTime: LiveData<String>
        get() = _formattedTime

    private var _startStopButtonState = MutableLiveData(STATES.START)
    val startStopButtonState: LiveData<STATES>
        get() = _startStopButtonState


    fun resetTimer() {
        stopTimer()
        milliseconds = 0
        updateStopWatchView(milliseconds)
        _startStopButtonState.value = STATES.START
    }

    fun startTimer() {
        mHandler = Handler(Looper.getMainLooper())
        mStatusChecker.run()
        _startStopButtonState.value = STATES.PAUSE
    }

    fun stopTimer() {
        mHandler?.removeCallbacks(mStatusChecker)
        _startStopButtonState.value = STATES.RESUME
    }

    private var mStatusChecker: Runnable = object : Runnable {
        override fun run() {
            try {
                milliseconds += 1
                Log.e("Time:", milliseconds.toString())
                updateStopWatchView(milliseconds)
            } finally {
                mHandler!!.postDelayed(this, mInterval)
            }
        }
    }

    private fun updateStopWatchView(millis: Long) {
        val formattedTime = Formatter.getFormattedStopWatchOld((millis))
        Log.e("FormattedTime:", formattedTime)
        _formattedTime.value = formattedTime
    }

}