package com.carlosjimz87.stopwatch.data.api

import com.carlosjimz87.stopwatch.data.repo.RecordRepository
import com.carlosjimz87.stopwatch.data.repo.RecordRepositoryImpl
import com.carlosjimz87.stopwatch.domain.data.RecordMapper
import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.utils.Constants
import com.google.gson.GsonBuilder
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val RECORD_DATE = "2021-02-26T09:25:23.112+0000"
const val RECORD_TIME = "09:25:23.112"
const val RECORD_ID = "603e972181087a6a8b94d92e"

@RunWith(JUnit4::class)
class RecordRepositoryTest : TestCase() {

    lateinit var service: ApiService
    lateinit var mapper: RecordMapper
    lateinit var repository: RecordRepository

    @Before
    fun setup() {

        service = Retrofit.Builder()
            .baseUrl(Constants.FAKE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)

        mapper = RecordMapper()

        repository = RecordRepositoryImpl(service, mapper)
    }

    @Test
    fun testListRecords() {

        runBlocking {
            val response = repository.listRecords()

            assertEquals(4, response.size)
            assertThat(response[0], instanceOf(Record::class.java))
            assertEquals(RECORD_DATE, response[0].datetime)
            assertEquals(RECORD_TIME, response[0].time)
        }
    }

    @Test
    fun testGetRecord() {

        runBlocking {
            val response = repository.getRecord(RECORD_ID)

            assertThat(response, instanceOf(Record::class.java))
            assertEquals(RECORD_DATE, response.datetime)
            assertEquals(RECORD_TIME, response.time)
        }
    }


    @Test
    fun testCreateRecord() {
        val newRecord = Record(
            datetime = RECORD_DATE,
            time = RECORD_TIME
        )
        runBlocking {
            val response = repository.createRecord(newRecord)
            assertTrue(response.success)
        }
    }


    @Test
    fun testDeleteRecord() {

        runBlocking {
            val response = repository.deleteRecord(RECORD_ID)
            assertFalse(response.success)
        }
    }
}

