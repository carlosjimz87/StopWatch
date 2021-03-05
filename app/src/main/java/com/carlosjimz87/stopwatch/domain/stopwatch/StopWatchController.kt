package com.carlosjimz87.stopwatch.domain.stopwatch

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.carlosjimz87.stopwatch.domain.viewmodels.StopWatchViewModel
import com.carlosjimz87.stopwatch.utils.Constants
import com.carlosjimz87.stopwatch.utils.Constants.INTERVAL
import com.carlosjimz87.stopwatch.utils.Extensions.formatTime

class StopWatchController(
    private var formattedTime: MutableLiveData<String>,
) : StopWatch {

    private var milliseconds = 0L
    private var mHandler: Handler? = null

    override fun startTimer(data: MutableLiveData<StopWatchViewModel.STATES>) {
        mHandler = Handler(Looper.getMainLooper())
        mStatusChecker.run()
        data.value = StopWatchViewModel.STATES.PAUSE
    }

    override fun pauseTimer(data: MutableLiveData<StopWatchViewModel.STATES>) {
        mHandler?.removeCallbacks(mStatusChecker)
        data.value = StopWatchViewModel.STATES.RESUME
    }

    override fun resetTimer(data: MutableLiveData<StopWatchViewModel.STATES>) {
        pauseTimer(data)
        milliseconds = 0L
        updateStopWatch()
        data.value = StopWatchViewModel.STATES.START
    }

    fun updateStopWatch() {
        val fTime = (milliseconds * INTERVAL).formatTime(false)
        formattedTime.value = fTime
    }

    private var mStatusChecker: Runnable = object : Runnable {
        override fun run() {
            try {
                 updateStopWatch()
                 milliseconds+=2       // using 2 increments to improve time precision

            } finally {
                mHandler!!.postDelayed(this, INTERVAL)
            }
        }
    }
}