package com.carlosjimz87.stopwatch.utils

import java.util.concurrent.TimeUnit

object Extensions {

    fun getFormattedStopWatchOld(ms: Long): String {
        var milliseconds = ms*1000
        val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
        milliseconds -= TimeUnit.HOURS.toMillis(hours)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
        milliseconds -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)

        return  "${if (hours < 10) "0" else ""}$hours:" +
                "${if (minutes < 10) "0" else ""}$minutes:" +
                "${if (seconds < 10) "0" else ""}$seconds"

    }

    fun String.formatRecordDate(): String{
        val lastChars = this.length - this.indexOf('.')
        return this.dropLast(lastChars).replace('T',' ')
    }


    fun Long.formatTime():String{

        val format = "%02d:%02d:%02d.%03d"
        val hours = TimeUnit.MILLISECONDS.toHours(this)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(this) - TimeUnit.HOURS.toMinutes(hours)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(this) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this))

//        val ms = if (!longFormat) this % 1000 else (this % 1000)/100
        val ms = this % 1000
//        return when {
//            hours > 0   ->   String.format(format, hours, minutes, seconds, ms)
//            minutes > 0 ->   String.format(format, minutes, seconds, ms)
//            else        ->   String.format(format, seconds, ms)
//
//        }
        return String.format(format, hours, minutes, seconds, ms)
    }
}