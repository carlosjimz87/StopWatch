package com.carlosjimz87.stopwatch.data.repo

import com.carlosjimz87.stopwatch.data.db.RecordsCache
import com.carlosjimz87.stopwatch.data.network.RecordsApi
import com.carlosjimz87.stopwatch.domain.models.Record


object RecordsRepository {
    enum class SOURCE{
        DB,
        NETWORK
    }

    var source:SOURCE=SOURCE.DB

    suspend fun listRecords():List<Record> {
        return when(source){
            SOURCE.DB -> RecordsCache.service.listRecords()
            SOURCE.NETWORK -> RecordsApi.service.listRecords()
        }

    }

    suspend fun createRecord(record:Record):Boolean {
        return when(source){
            SOURCE.DB -> RecordsCache.service.createRecord(record)
            SOURCE.NETWORK -> RecordsApi.service.createRecord(record)
        }

    }

    suspend fun deleteRecord(recordId:String):Boolean {
        return when(source){
            SOURCE.DB -> RecordsCache.service.deleteRecord(recordId)
            SOURCE.NETWORK -> RecordsApi.service.deleteRecord(recordId)
        }

    }

}
