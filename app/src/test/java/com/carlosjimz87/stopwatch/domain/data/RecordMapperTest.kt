package com.carlosjimz87.stopwatch.domain.data

import com.carlosjimz87.stopwatch.data.models.RecordResponse
import com.carlosjimz87.stopwatch.domain.models.Record
import junit.framework.TestCase


class RecordMapperTest : TestCase() {

    private  var mapper = RecordMapper()

    fun testMapToResponse() {
        val record = Record(
            "2021-02-26T09:25:23.112+0000",
            "09:25:23.112"
        )
        val recordEntity: RecordResponse = mapper.mapToResponse(record)

        assertEquals(record.datetime,recordEntity.datetime)
        assertEquals(record.time,recordEntity.time)
    }

    fun testMapToEntity() {
        val recordEntity = RecordResponse(
            "2021-02-26T09:25:23.112+0000",
            "09:25:23.112"
        )
        val record:Record = mapper.mapFromResponse(recordEntity)

        assertEquals(recordEntity.datetime,record.datetime)
        assertEquals(recordEntity.time,record.time)
    }

}