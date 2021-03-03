package com.carlosjimz87.stopwatch.data.api

import com.carlosjimz87.stopwatch.data.repo.RecordRepository
import com.carlosjimz87.stopwatch.data.repo.RecordRepositoryImpl
import com.carlosjimz87.stopwatch.domain.data.RecordMapper
import com.carlosjimz87.stopwatch.domain.models.Record
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class RecordRepositoryTest : TestCase() {

    private val RECORD_DATE = "2021-02-26T09:25:23.112+0000"
    private val RECORD_TIME = "09:25:23.112"
    private val RECORD_ID = "603e972181087a6a8b94d92e"

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var mapper: RecordMapper
    private lateinit var repository: RecordRepository

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mapper = RecordMapper()

    }
    @After
    override fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testListRecords() {

        val response = repository.listRecords()

        assertEquals(4, response.size)
        assertThat(response[0], instanceOf(Record::class.java))
        assertEquals(RECORD_DATE, response[0].datetime)
        assertEquals(RECORD_TIME, response[0].time)

    }

    @Test
    fun testGetRecord() {

        val response = repository.getRecord(RECORD_ID)

        assertThat(response, instanceOf(Record::class.java))
        assertEquals(RECORD_DATE, response.datetime)
        assertEquals(RECORD_TIME, response.time)

    }


    @Test
    fun testCreateRecord() {
        val newRecord = Record(
            datetime = RECORD_DATE,
            time = RECORD_TIME
        )

        val response = repository.createRecord(newRecord)
        assertTrue(response.success)

    }


    @Test
    fun testDeleteRecord() {

        val response = repository.deleteRecord(RECORD_ID)
        assertFalse(response.success)

    }
}

