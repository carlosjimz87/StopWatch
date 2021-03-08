package com.carlosjimz87.stopwatch.utils

import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.utils.Constants.INIT_TIME
import java.util.concurrent.TimeUnit
import kotlin.math.abs

object Extensions {

    fun Long.formatTime():String{

        val format = "%02d:%02d:%02d.%03d"
        val hours = TimeUnit.MILLISECONDS.toHours(this)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(this) - TimeUnit.HOURS.toMinutes(hours)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(this) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this))

        val ms = this % 1000

        return String.format(format, hours, minutes, seconds, ms)
    }

    fun String.formatRecordDate(): String{
        val lastChars = this.length - this.indexOf('.')
        return this.dropLast(lastChars).replace('T',' ')
    }


    fun String.isTimeEmpty():Boolean{
        return  (this.isEmpty()) ||
                (this==INIT_TIME)
    }


    fun Record.isEmpty():Boolean{
        return  (this.id.isEmpty()) &&
                (this.time.isEmpty()) &&
                (this.datetime.isEmpty())
    }


    fun Record.unique():Long{
        return abs((this.datetime+this.time).hashCode().toLong())
    }

   fun List<Record>.sort(): List<Record> {
       return this.sortedByDescending {
           it.datetime
       }
   }
}