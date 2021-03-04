package com.carlosjimz87.stopwatch

import android.content.Context
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.carlosjimz87.stopwatch.data.base.Api
import com.carlosjimz87.stopwatch.data.db.Database
import com.carlosjimz87.stopwatch.data.db.RecordsCache
import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.utils.SDBR
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
class SharedPrefsTest {

    lateinit var instrumentationContext: Context

    private lateinit var fakeDatabase: Api
    @Before
    fun setUp(){
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context

        fakeDatabase = RecordsCache.service
        (fakeDatabase as Database).init(instrumentationContext)

    }

    @Test
    fun testEmptyRecords() = runBlocking { // that will allow to wait for coroutine

        val record = fakeDatabase.getRecord(SDBR.records[0].id)
        val emptyRecord = Record(
            "","",""
        )
        assertEquals(record,emptyRecord)
    }


    @Test
    fun testListCreateDeleteRecords() = runBlocking { // that will allow to wait for coroutine

        // Check first if 0 records
        var records = fakeDatabase.listRecords()
        assertEquals(records.size,0)

        // Create 4 records
        var result = false
        SDBR.records.forEach {
            result = fakeDatabase.createRecord(it)
            assertEquals(result, true)
        }

        // Check now if 4 records
        records = fakeDatabase.listRecords()
        assertEquals(records.size,4)

        // Delete 4 records
        result = false
        SDBR.records.forEach {
            result = fakeDatabase.deleteRecord(it.id)
            assertEquals(result, true)
        }

        // Check if 0 records again
        records = fakeDatabase.listRecords()
        assertEquals(records.size,0)

    }

}