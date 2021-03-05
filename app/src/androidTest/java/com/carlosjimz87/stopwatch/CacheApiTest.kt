package com.carlosjimz87.stopwatch

import android.content.Context
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.carlosjimz87.stopwatch.data.base.Api
import com.carlosjimz87.stopwatch.data.db.Database
import com.carlosjimz87.stopwatch.data.db.CacheApi
import com.carlosjimz87.stopwatch.utils.SDBR
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
class CacheApiTest {

    private lateinit var instrumentationContext: Context

    private lateinit var fakeDatabase: Api

    @Before
    fun setUp() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context

        fakeDatabase = CacheApi.service
        (fakeDatabase as Database).init(instrumentationContext)

    }

    @Test
    fun testAllOpsCacheApi() = runBlocking { // that will allow to wait for coroutine
        //clear preferences
        clearSharedPrefs()

        // Check first if 0 records
        var records = fakeDatabase.listRecords()
        assertEquals(0, records.size)

        // Create 4 records

        SDBR.records.forEach {
            val result = fakeDatabase.createRecord(it)
            assertEquals(true, result)
        }

        // Check now if 4 records
        records = fakeDatabase.listRecords()
        assertEquals(4, records.size)

        // Delete 4 records

        SDBR.records.forEach {
            val result = fakeDatabase.deleteRecord(it.id)
            assertEquals(true, result)
        }

        // Check if 0 records again
        records = fakeDatabase.listRecords()
        assertEquals(0, records.size)

    }

        private fun clearSharedPrefs() = runBlocking {
        (fakeDatabase as Database).clearRecords()
    }


//    @Test
//    fun testGetEmptyRecord() = runBlocking { // that will allow to wait for coroutine
//        //clear preferences
//        clearSharedPrefs()
//
//        val record = fakeDatabase.getRecord(SDBR.records[0].id)
//        val emptyRecord = Record(
//            "", "", ""
//        )
//        assertEquals(emptyRecord, record)
//    }
//
//    @Test
//    fun testDeleteNullRecord() = runBlocking { // that will allow to wait for coroutine
//        //clear preferences
//        clearSharedPrefs()
//
//        val result = fakeDatabase.deleteRecord(SDBR.newRecord.id)
//        assertEquals(false, result)
//    }
//
//    @Test
//    fun testCreateTwice() = runBlocking { // that will allow to wait for coroutine
//        //clear preferences
//        clearSharedPrefs()
//
//        var result = fakeDatabase.createRecord(SDBR.newRecord)
//        assertEquals(true, result)
//
//        result = fakeDatabase.createRecord(SDBR.newRecord)
//        assertEquals(false, result)
//    }
//

}