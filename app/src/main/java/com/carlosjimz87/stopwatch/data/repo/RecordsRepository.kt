package com.carlosjimz87.stopwatch.data.repo

import com.carlosjimz87.stopwatch.data.db.RecordsCache
import com.carlosjimz87.stopwatch.data.network.RecordsApi
import com.carlosjimz87.stopwatch.domain.models.Record


class RecordsRepository {
    enum class SOURCE{
        DB,
        NETWORK
    }

    suspend fun listRecords(source:SOURCE):List<Record> {
        return when(source){
            SOURCE.DB -> RecordsCache.service.listRecords()
            SOURCE.NETWORK -> RecordsApi.service.listRecords()
        }

    }

}
