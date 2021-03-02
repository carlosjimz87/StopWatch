package com.carlosjimz87.stopwatch.domain.timer

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData

import com.carlosjimz87.stopwatch.utils.Constants
import com.carlosjimz87.stopwatch.utils.Formatter
import com.carlosjimz87.stopwatch.domain.timer.Timer.*

class TimerImpl(var formattedTime: MutableLiveData<String>) : Timer {

    private var milliseconds = 0L
    private val mInterval = Constants.TIMER_INTERVAL
    private var mHandler: Handler? = null
    private var status = STATES.START

    override fun startTimer(data: MutableLiveData<STATES>) {
        mHandler = Handler(Looper.getMainLooper())
        mStatusChecker.run()
        status = STATES.PAUSE
        data.value = STATES.PAUSE
    }


    override fun stopTimer(data: MutableLiveData<STATES>) {
        mHandler?.removeCallbacks(mStatusChecker)
        status = STATES.RESUME
        data.value = STATES.RESUME
    }

    override fun resetTimer(data: MutableLiveData<STATES>) {
        stopTimer(data)
        milliseconds = 0
        updateStopWatch(milliseconds)
        status = STATES.START
        data.value = STATES.START
    }

    fun updateStopWatch(millis: Long) {
        val fTime = Formatter.getFormattedStopWatchOld((millis))
        Log.e("FormattedTime:", fTime)
        formattedTime.value = fTime
    }

    private var mStatusChecker: Runnable = object : Runnable {
        override fun run() {
            try {
                milliseconds += 1
                Log.e("Time:", milliseconds.toString())
                updateStopWatch(milliseconds)
            } finally {
                mHandler!!.postDelayed(this, mInterval)
            }
        }
    }
}