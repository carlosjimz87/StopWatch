package com.carlosjimz87.stopwatch.data.base

import com.carlosjimz87.stopwatch.domain.models.Record

interface Api {

    suspend fun listRecords(): List<Record>
    suspend fun getRecord(recordId: String): Record
    suspend fun deleteRecord(record: Record): Boolean
    suspend fun createRecord(record: Record): Boolean
}