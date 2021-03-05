package com.carlosjimz87.stopwatch.utils

import com.carlosjimz87.stopwatch.domain.models.Record
import java.util.concurrent.TimeUnit

object Extensions {

    fun String.formatRecordDate(): String{
        val lastChars = this.length - this.indexOf('.')
        return this.dropLast(lastChars).replace('T',' ')
    }


    fun Long.formatTime():String{

        val format = "%02d:%02d:%02d.%03d"
        val hours = TimeUnit.MILLISECONDS.toHours(this)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(this) - TimeUnit.HOURS.toMinutes(hours)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(this) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this))

        val ms = this % 1000

        return String.format(format, hours, minutes, seconds, ms)
    }

    fun Record.isEmpty():Boolean{
        return  (this.id.isEmpty()) &&
                (this.time.isEmpty()) &&
                (this.datetime.isEmpty())
    }
}