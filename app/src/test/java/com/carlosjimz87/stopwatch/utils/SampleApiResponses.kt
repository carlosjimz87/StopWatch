package com.carlosjimz87.stopwatch.utils

import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.utils.Constants.COLLECTION_ID

typealias SAR = SampleApiResponses

object SampleApiResponses{
    const val idForRecord: String = "603e91da81087a6a8b94d67c"
    const val idForRecord1: String = "603ce4840866664b1085dffb"
    const val idForRecord2: String = "603e972181087a6a8b94d92e"
    const val idForRecord3: String = "603e97450866664b10874da7"

    val record = Record(
        id=idForRecord,
        datetime = "2021-02-26T09:25:23.112+0000",
        time = "09:25:23.112"
    )

    const val newRecordId: String = "63e97450866664b10999zz9"

    val newRecord = Record(
        id=newRecordId,
        datetime = "2100-12-31T23:59:59.001+0000",
        time = "00:00:00.001"
    )

    val records: List<Record> = listOf(
        Record("603e91da81087a6a8b94d67c","2021-03-02T18:36:59.123+0000","00:00:31.234"),
        Record("603ce4840866664b1085dffb","2021-03-02T09:35:22.354+0000","00:00:11.236"),
        Record("603e972181087a6a8b94d92e","2021-03-01T11:22:63.232+0000","00:00:15.159"),
        Record("603e97450866664b10874da7","2021-03-01T09:55:33.231+0000","00:00:15.392")
    )

    fun listRecords(): String {
        return """{
        "records": [
            {
                "id": "$idForRecord"
            },
            {
                "id": "$idForRecord1"
            },
            {
                "id": "$idForRecord2"
            },
            {
                "id": "$idForRecord3"
            }
        ],
        "success": true
    }"""
    }

    fun getRecord(): String {
        return """
            {"datetime": "${record.datetime}",
            "time": "${record.time}" 
            }"""
    }

    fun deleteRecord(): String {
        return """{
        "success": true,
        "id": "$idForRecord",
        "message": "Bin $idForRecord is deleted successfully. 0 versions removed."
    }"""
    }


    fun createRecord(): String {
        return """{
        "success": true,
        "data": {
            "datetime": "${newRecord.datetime}",
            "time": "${newRecord.time}"
        },
        "id": "$newRecordId",
        "collectionID": "$COLLECTION_ID",
        "private": false
    }"""
    }
}