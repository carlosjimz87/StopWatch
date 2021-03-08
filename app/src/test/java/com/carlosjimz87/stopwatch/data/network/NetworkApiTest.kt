package com.carlosjimz87.stopwatch.data.network

import com.carlosjimz87.stopwatch.data.base.Api
import com.carlosjimz87.stopwatch.utils.Constants
import com.carlosjimz87.stopwatch.utils.SAR
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class NetworkApiTest {

    private lateinit var api: Api

    @Before
    fun setUp() {
        RetrofitApi.BASE_URL = Constants.POSTMAN_API_BASE_URL
        api = RecordsApi.service
    }

    @Test
    fun testAllReqRecordsAPI()= runBlocking {

        // GEt first if initial number records
        val initNumRecords = api.listRecords().size

        // Create 4 new records
        SAR.records.forEach {
            val result = api.createRecord(it)
            Assert.assertEquals(true, result)
        }

        // Check now if 4 records were added
        val records = api.listRecords()
        if(RetrofitApi.BASE_URL == Constants.API_BASE_URL)
            Assert.assertEquals(initNumRecords+4, records.size)
        else
            Assert.assertEquals(initNumRecords, records.size)

        // Delete same 4 records
        records.forEach {
            val result = api.deleteRecord(it)
            Assert.assertEquals(true, result)
        }

        // Check if number of records now is the same at the beginning
        val finalNumRecords = api.listRecords().size
        Assert.assertEquals(initNumRecords, finalNumRecords)
    }
//
//    @Test
//    fun testListRecords() = runBlocking { // that will allow to wait for coroutine
//
//        val records = api.listRecords()
//
//        Assert.assertEquals( 4,records.size)
//        Assert.assertEquals(records[0].id, SAR.idForRecord)
//        Assert.assertEquals(records[1].id, SAR.idForRecord1)
//        Assert.assertEquals(records[2].id, SAR.idForRecord2)
//        Assert.assertEquals(records[3].id, SAR.idForRecord3)
//    }
//
//    @Test
//    fun testGetRecord() = runBlocking { // that will allow to wait for coroutine
//
//        val record = api.getRecord(SAR.idForRecord)
//
//        MatcherAssert.assertThat(record, CoreMatchers.instanceOf(Record::class.java))
//        Assert.assertEquals(record.datetime, SAR.record.datetime)
//        Assert.assertEquals(record.time, SAR.record.time)
//    }
//
//
//    @Test
//    fun testDeleteRecord() = runBlocking { // that will allow to wait for coroutine
//
//        val result = api.deleteRecord(SAR.idForRecord)
//
//        Assert.assertEquals(true, result)
//    }
//
//    @Test
//    fun testCreateRecord() = runBlocking { // that will allow to wait for coroutine
//
//        val result = api.createRecord(SAR.record)
//
//        Assert.assertEquals(true, result)
//    }
}
