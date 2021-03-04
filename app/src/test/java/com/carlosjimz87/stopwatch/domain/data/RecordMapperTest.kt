package com.carlosjimz87.stopwatch.domain.data

import com.carlosjimz87.stopwatch.data.models.RecordResponse
import com.carlosjimz87.stopwatch.domain.models.Record
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class RecordMapperTest : TestCase() {

    @Test
    fun testMapToResponse() {
        val record = Record(
            id = "",
            datetime = "2021-02-26T09:25:23.112+0000",
            time = "09:25:23.112"
        )
        val recordEntity: RecordResponse = RecordMapper.mapToResponse(record)

        assertEquals(record.datetime, recordEntity.datetime)
        assertEquals(record.time, recordEntity.time)
    }

    @Test
    fun testMapToEntity() {
        val recordEntity = RecordResponse(
            "2021-02-26T09:25:23.112+0000",
            "09:25:23.112"
        )
        val record: Record = RecordMapper.mapFromResponse(recordEntity,"")

        assertEquals(recordEntity.datetime, record.datetime)
        assertEquals(recordEntity.time, record.time)
    }

}