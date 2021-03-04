package com.carlosjimz87.stopwatch.data.db

import android.content.Context
import com.carlosjimz87.stopwatch.data.base.Api
import com.carlosjimz87.stopwatch.domain.models.Record


class Database : Api {
    lateinit var context: Context

    fun init(context: Context) {
        this.context = context
    }

    override suspend fun listRecords(): List<Record> {
        return try {
            SharedPrefsManager.getRecords(context)
        } catch (e: Exception) {
            return listOf()   // return empty list
        }
    }

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

    override suspend fun deleteRecord(recordId: String): Boolean {
        return try {

            val records = SharedPrefsManager.getRecords(context)
            val newRecords = records.filter {
                it.id == recordId
            }
            SharedPrefsManager.setRecords(context, newRecords)
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun createRecord(record: Record): Boolean {
        return try {

            val records = SharedPrefsManager.getRecords(context).toMutableList()
            records.add(record)
            SharedPrefsManager.setRecords(context, records.toList())
            true
        } catch (e: Exception) {
            false
        }
    }


}

object RecordsCache {

    val service: Api by lazy {
        Database()
    }

}