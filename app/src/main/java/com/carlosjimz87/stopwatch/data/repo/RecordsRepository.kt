package com.carlosjimz87.stopwatch.data.repo

import android.content.Context
import com.carlosjimz87.stopwatch.data.db.Database
import com.carlosjimz87.stopwatch.data.db.CacheApi
import com.carlosjimz87.stopwatch.data.network.RecordsApi
import com.carlosjimz87.stopwatch.domain.models.Record
import java.lang.Exception


object RecordsRepository {
    enum class SOURCE{
        DB,
        API
    }
    private var source:SOURCE=SOURCE.DB

    fun init(context: Context) {
        (CacheApi.service as Database).init(context)
    }

    fun toDB():RecordsRepository{
        source = SOURCE.DB
        return this
    }

    fun toAPI():RecordsRepository{
        source = SOURCE.API
        return this
    }

    @Throws(Exception::class)
    suspend fun listRecords():List<Record> {
        return when(source){
            SOURCE.DB -> CacheApi.service.listRecords()
            SOURCE.API -> RecordsApi.service.listRecords()
        }

    }

    @Throws(Exception::class)
    suspend fun createRecord(record:Record):Boolean {

        return when(source){
            SOURCE.DB -> CacheApi.service.createRecord(record)
            SOURCE.API -> RecordsApi.service.createRecord(record)
        }

    }

    @Throws(Exception::class)
    suspend fun deleteRecord(recordId:String):Boolean {
        return when(source){
            SOURCE.DB -> CacheApi.service.deleteRecord(recordId)
            SOURCE.API -> RecordsApi.service.deleteRecord(recordId)
        }

    }

}
