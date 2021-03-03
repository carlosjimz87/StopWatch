package com.carlosjimz87.stopwatch.data.repo

import com.carlosjimz87.stopwatch.data.models.CreateResponse
import com.carlosjimz87.stopwatch.data.models.DeleteResponse
import com.carlosjimz87.stopwatch.domain.models.Record

interface RecordRepository {
    fun listRecords() : List<Record>
    fun getRecord(recordId: String) : Record
    fun createRecord(record: Record) : CreateResponse
    fun deleteRecord(recordId: String) : DeleteResponse
}