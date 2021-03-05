package com.carlosjimz87.stopwatch.data.network

import android.util.Log
import com.carlosjimz87.stopwatch.data.base.Api
import com.carlosjimz87.stopwatch.data.models.RecordResponse
import com.carlosjimz87.stopwatch.domain.data.RecordMapper
import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.utils.Constants

class NetworkApi : Api {

    @Throws(Exception::class)
    override suspend fun listRecords(): List<Record> {
        val listResponse = RetrofitApi.service.listRecords(
            Constants.COLLECTION_ID,
            RetrofitApi.headers()
        )

        Log.d("STOPWATCH: ","[REQ:GET] to: ${RetrofitApi.BASE_URL} responded: $listResponse")
        return listResponse.records.map { item ->
            getRecord(item.id)
        }
    }

    @Throws(Exception::class)
    override suspend fun getRecord(recordId: String): Record {

        val recordResponse: RecordResponse = RetrofitApi.service.getRecord(
            recordId,
            RetrofitApi.headers()
        )

        Log.d("STOPWATCH","[REQ:GET] to: ${RetrofitApi.BASE_URL} responded: $recordResponse")

        return RecordMapper.mapFromResponse(recordResponse,recordId)
    }

    @Throws(Exception::class)
    override suspend fun deleteRecord(recordId: String): Boolean {
        val response = RetrofitApi.service.deleteRecord(
            recordId,
            RetrofitApi.headers()
        )

        Log.d("STOPWATCH: ","[REQ:DELETE] to: ${RetrofitApi.BASE_URL} responded: $response")
        return response.success
    }

    @Throws(Exception::class)
    override suspend fun createRecord(record: Record): Boolean {
        val response =  RetrofitApi.service.createRecord(
            record,
            RetrofitApi.headers()
        )

        Log.d("STOPWATCH: ","[REQ:CREATE] to: ${RetrofitApi.BASE_URL} responded: $response")
        return response.success
    }

}


object RecordsApi{

    val service: Api by lazy {
        NetworkApi()
    }

}