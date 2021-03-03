package com.carlosjimz87.stopwatch.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Record(
    val datetime: String,
    val time: String
) : Parcelable