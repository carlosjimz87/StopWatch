package com.carlosjimz87.stopwatch.data.network

import com.carlosjimz87.stopwatch.data.base.Api
import com.carlosjimz87.stopwatch.data.models.RecordResponse
import com.carlosjimz87.stopwatch.domain.data.RecordMapper
import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.utils.Constants

class NetworkApi : Api {
    override suspend fun listRecords(): List<Record> {
        val response: List<Record> = listOf()

        val listResponse = RetrofitApi.service.listRecords(Constants.COLLECTION_ID,
            Constants.API_KEY_VALUE)

        listResponse.records.map { item ->
            response.also { getRecord(item.id) }
        }

        return response
    }

    override suspend fun getRecord(recordId: String): Record {
        lateinit var record: Record

        val recordResponse: RecordResponse = RetrofitApi.service.getRecord(recordId,
            Constants.API_KEY_VALUE)

        record = RecordMapper.mapFromResponse(recordResponse,recordId)

        return Record(
            id=recordId,
            datetime = record.datetime,
            time = record.time
        )
    }

    override suspend fun deleteRecord(recordId: String): Boolean {
        val response = RetrofitApi.service.deleteRecord(recordId, Constants.API_KEY_VALUE)
        return response.success
    }

    override suspend fun createRecord(record: Record): Boolean {
        val response =  RetrofitApi.service.createRecord(record, Constants.API_KEY_VALUE)
        return response.success
    }

}


object RecordsApi{

    val service: Api by lazy {
        NetworkApi()
    }

}