package com.carlosjimz87.stopwatch.utils

import com.carlosjimz87.stopwatch.domain.models.Record

typealias SDBR = SampleDBResponses
object SampleDBResponses {

    val records: List<Record> = listOf(
        Record("603e91da81087a6a8b94d67c","2021-03-02T18:36:59.123+0000","00:00:31.234"),
        Record("603ce4840866664b1085dffb","2021-03-02T09:35:22.354+0000","00:00:11.236"),
        Record("603e972181087a6a8b94d92e","2021-03-01T11:22:63.232+0000","00:00:15.159"),
        Record("603e97450866664b10874da7","2021-03-01T09:55:33.231+0000","00:00:15.392")
    )

    val newRecord = Record(
        id="63e97450866664b10999zz9",
        datetime = "2100-12-31T23:59:59.001+0000",
        time = "00:00:00.001"
    )

}