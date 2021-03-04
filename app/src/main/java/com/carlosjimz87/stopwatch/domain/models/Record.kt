package com.carlosjimz87.stopwatch.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Record(
    val id: String,
    val datetime: String,
    val time: String
) : Parcelable