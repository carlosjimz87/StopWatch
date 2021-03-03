package com.carlosjimz87.stopwatch.data.repo

import com.carlosjimz87.stopwatch.data.api.ApiService
import com.carlosjimz87.stopwatch.data.models.CreateResponse
import com.carlosjimz87.stopwatch.data.models.DeleteResponse
import com.carlosjimz87.stopwatch.domain.data.RecordMapper
import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.utils.Constants.API_KEY_VALUE
import com.carlosjimz87.stopwatch.utils.Constants.COLLECTION_ID

class RecordRepositoryImpl (
    private val service : ApiService,
    private val mapper: RecordMapper
    ): RecordRepository {

    override suspend fun listRecords(): List<Record> {
        val listResponse = service.listRecords(COLLECTION_ID, API_KEY_VALUE)

        return listResponse.records.map {
            getRecord(it.id)
        }
    }

    override suspend fun getRecord(recordId: String): Record {
        val recordResponse = service.getRecord(recordId, API_KEY_VALUE)
        return mapper.mapFromResponse(recordResponse)
    }

    override suspend fun createRecord(record: Record): CreateResponse {
        return service.createRecord(record,API_KEY_VALUE)
    }

    override suspend fun deleteRecord(recordId: String): DeleteResponse {
        return service.deleteRecord(recordId,API_KEY_VALUE)
    }


}
