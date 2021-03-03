package com.carlosjimz87.stopwatch.data.repo

import com.carlosjimz87.stopwatch.data.models.CreateResponse
import com.carlosjimz87.stopwatch.data.models.DeleteResponse
import com.carlosjimz87.stopwatch.domain.models.Record

interface RecordRepository {
    suspend fun listRecords() : List<Record>
    suspend fun getRecord(recordId: String) : Record
    suspend fun createRecord(record: Record) : CreateResponse
    suspend fun deleteRecord(recordId: String) : DeleteResponse
}