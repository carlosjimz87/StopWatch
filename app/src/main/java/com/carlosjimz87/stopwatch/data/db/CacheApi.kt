package com.carlosjimz87.stopwatch.data.db

import android.content.Context
import com.carlosjimz87.stopwatch.data.base.Api
import com.carlosjimz87.stopwatch.domain.models.Record


class DatabaseApi : Api {
    lateinit var context: Context

    fun init(context: Context) {
        this.context = context
    }

    @Throws(Exception::class)
    override suspend fun listRecords(): List<Record> {
        return try {
            SharedPrefsManager.getRecords(context)
        } catch (e: Exception) {
            return listOf()   // return empty list
        }
    }

    @Throws(Exception::class)
    override suspend fun getRecord(recordId: String): Record {
        val records = SharedPrefsManager.getRecords(context)
        return try {
            records.single {
                it.id == recordId
            }
        } catch (e: Exception) {
            Record("", "", "")    // return empty object
        }

    }

    @Throws(Exception::class)
    override suspend fun deleteRecord(record: Record): Boolean {
        return try {

            val records = SharedPrefsManager.getRecords(context)
            val newRecords = records.filterNot {
                it.datetime == record.datetime && it.time == record.time
            }
            if(newRecords.size != records.size){
                SharedPrefsManager.setRecords(context, newRecords)
                true
            }
            else
                false

        } catch (e: Exception) {
            false
        }
    }

    @Throws(Exception::class)
    override suspend fun createRecord(record: Record): Boolean {

            val records = SharedPrefsManager.getRecords(context).toMutableList()

            // check if not exists first
            if(!records.contains(record))
                records.add(record)
            else{ return false }

            SharedPrefsManager.setRecords(context, records.toList())
        return true
    }

    @Throws(Exception::class)
    suspend fun clearRecords(): Boolean{
        return try {
            SharedPrefsManager.clearRecords(context)
            true
        } catch (e: Exception) {
            false
        }
    }

}

object CacheApi {

    val service: Api by lazy {
        DatabaseApi()
    }

}