package com.carlosjimz87.stopwatch.utils

import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.utils.Extensions.unique
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ExtensionsTest {


    @Test
    fun testToULong() {
        val record = Record(
            id = "",
            datetime = SAR.record.datetime,
            time = SAR.record.time,
        )

        val record2 = Record(
            id = "",
            datetime = SAR.newRecord.datetime,
            time = SAR.newRecord.time,
        )

        val slightlyDifferentRecord2 = Record(
                id= "",
                datetime = "2100-12-31T23:59:59.001+0000",
                time = "00:00:00.002"
        )

        MatcherAssert.assertThat(record.unique(), CoreMatchers.instanceOf(Long::class.java))
        MatcherAssert.assertThat(record2.unique(), CoreMatchers.instanceOf(Long::class.java))
        MatcherAssert.assertThat(slightlyDifferentRecord2.unique(), CoreMatchers.instanceOf(Long::class.java))
        assertEquals(850002282L, record.unique())
        assertEquals(651810834L, record2.unique())
        assertEquals(651810835L, slightlyDifferentRecord2.unique())
    }
}