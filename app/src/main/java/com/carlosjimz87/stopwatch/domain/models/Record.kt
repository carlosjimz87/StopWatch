package com.carlosjimz87.stopwatch.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Record(
    val id: String,
    val datetime: String,
    val time: String
) : Parcelable{

    override fun equals(other: Any?): Boolean {

        if(javaClass!=other?.javaClass) return false

        other as Record

        if(id!= other.id) return false
        if(datetime!= other.datetime) return false
        if(time!= other.time) return false

        return true
    }
}