package com.carlosjimz87.stopwatch.data.db

import com.carlosjimz87.stopwatch.data.base.Api
import com.carlosjimz87.stopwatch.domain.models.Record


class FakeDatabase: Api {
    override suspend fun listRecords(): List<Record> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecord(recordId: String): Record {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRecord(recordId: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun createRecord(record: Record): Boolean {
        TODO("Not yet implemented")
    }


}

object RecordsCache{

    val service: Api by lazy {
        FakeDatabase()
    }

}