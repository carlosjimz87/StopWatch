package com.carlosjimz87.stopwatch.domain.stopwatch

import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.domain.viewmodels.StopWatchViewModel
import com.carlosjimz87.stopwatch.utils.Constants.INTERVAL
import com.carlosjimz87.stopwatch.utils.Extensions.formatTime
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
class StopWatchController(
    private var formattedTime: MutableLiveData<String>,
    private var state: MutableLiveData<StopWatchViewModel.STATES>,
    private var saveRecord: MutableLiveData<Record>,
)  {

    private var milliseconds = 0L
    private var mHandler: Handler? = null

    fun startTimer() {
        mHandler = Handler(Looper.getMainLooper())
        mStatusChecker.run()
        state.value = StopWatchViewModel.STATES.PAUSE
    }

    fun pauseTimer() {
        mHandler?.removeCallbacks(mStatusChecker)
        state.value = StopWatchViewModel.STATES.RESUME
    }

    fun resetTimer() {
        pauseTimer()
        recordRecord()
        milliseconds = 0L
        updateStopWatch()
        state.value = StopWatchViewModel.STATES.START
    }

    private fun recordRecord(){
        saveRecord.value = Record(
            id = "",
            datetime = LocalDateTime.now().toString(),
            time = formattedTime.value!!
        )

    }
    fun updateStopWatch() {
        val fTime = (milliseconds * INTERVAL).formatTime()
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