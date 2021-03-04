package com.carlosjimz87.stopwatch.data.network

import com.carlosjimz87.stopwatch.data.models.RecordResponse
import com.carlosjimz87.stopwatch.utils.Constants.API_KEY_VALUE
import com.carlosjimz87.stopwatch.utils.Constants.COLLECTION_ID
import com.carlosjimz87.stopwatch.utils.SAR
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class RecordsApiTest {
    private var mockWebServer = MockWebServer()

    private lateinit var apiService: RetrofitService

    @Before
    fun setUp() {

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        mockWebServer.start()

        apiService = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(mockWebServer.url("/")) // note the URL is different from production one
            .build()
            .create(RetrofitService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testListRecords() = runBlocking { // that will allow to wait for coroutine
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(
                    SAR.listRecords()
                )
        )
        val response = apiService.listRecords(COLLECTION_ID, API_KEY_VALUE)

        assertEquals(true, response.success)
        assertEquals(response.records[0].id, SAR.idForRecord)
        assertEquals(response.records[1].id, SAR.idForRecord1)
        assertEquals(response.records[2].id, SAR.idForRecord2)
        assertEquals(response.records[3].id, SAR.idForRecord3)
    }

    @Test
    fun testGetRecord() = runBlocking { // that will allow to wait for coroutine
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(
                    SAR.getRecord()
                )
        )
        val record = apiService.getRecord(SAR.idForRecord, API_KEY_VALUE)

        assertThat(record, CoreMatchers.instanceOf(RecordResponse::class.java))
        assertEquals(record.datetime, SAR.record.datetime)
        assertEquals(record.time, SAR.record.time)
    }


    @Test
    fun testDeleteRecord() = runBlocking { // that will allow to wait for coroutine
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(
                    SAR.deleteRecord()
                )
        )
        val response = apiService.deleteRecord(SAR.idForRecord, API_KEY_VALUE)

        assertEquals(response.success,true)
        assertEquals(response.id, SAR.idForRecord)
    }

    @Test
    fun testCreateRecord() = runBlocking { // that will allow to wait for coroutine
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(
                    SAR.createRecord()
                )
        )
        val response = apiService.createRecord(SAR.record, API_KEY_VALUE)

        assertEquals(response.success,true)
        assertEquals(response.id, SAR.newRecordId)
        MatcherAssert.assertThat(response.data, CoreMatchers.instanceOf(RecordResponse::class.java))
        assertEquals(response.data.datetime, SAR.newRecord.datetime)
        assertEquals(response.data.time, SAR.newRecord.time)
    }
}