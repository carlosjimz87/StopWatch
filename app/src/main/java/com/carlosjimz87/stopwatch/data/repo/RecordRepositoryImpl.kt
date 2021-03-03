package com.carlosjimz87.stopwatch.data.repo

import com.carlosjimz87.stopwatch.data.api.RecordsApiService
import com.carlosjimz87.stopwatch.data.models.CreateResponse
import com.carlosjimz87.stopwatch.data.models.DeleteResponse
import com.carlosjimz87.stopwatch.data.models.RecordResponse
import com.carlosjimz87.stopwatch.domain.data.RecordMapper
import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.utils.Constants.API_KEY_VALUE
import com.carlosjimz87.stopwatch.utils.Constants.COLLECTION_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RecordRepositoryImpl (
    private val service:RecordsApiService,
    private val mapper: RecordMapper
    ): RecordRepository {

    override  fun listRecords(): List<Record> {
        val response: List<Record> = listOf()

        CoroutineScope(IO).launch {
            val listResponse = service.listRecords(COLLECTION_ID, API_KEY_VALUE)

            listResponse.records.map { item ->
                response.also { getRecord(item.id) }
            }

        }

        return response
    }

    override  fun getRecord(recordId: String): Record {
        lateinit var record: Record
        CoroutineScope(IO).launch {
            val recordResponse: RecordResponse = service.getRecord(recordId, API_KEY_VALUE)

            record = mapper.mapFromResponse(recordResponse)
        }
        return record
    }

    override  fun createRecord(record: Record): CreateResponse {
        lateinit var create: CreateResponse
        CoroutineScope(IO).launch {
            create = service.createRecord(record,API_KEY_VALUE)
        }
        return create
    }

    override  fun deleteRecord(recordId: String): DeleteResponse {

        lateinit var delete: DeleteResponse
        CoroutineScope(IO).launch {
            delete = service.deleteRecord(recordId,API_KEY_VALUE)
        }
        return delete
    }


}
