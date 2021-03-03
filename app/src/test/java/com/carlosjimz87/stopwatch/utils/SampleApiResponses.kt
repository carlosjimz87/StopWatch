package com.carlosjimz87.stopwatch.utils

import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.utils.Constants.COLLECTION_ID

typealias SAR = SampleApiResponses

object SampleApiResponses{
    const val idForRecord: String = "603e91da81087a6a8b94d67c"
    const val idForRecord1: String = "603e91da81087a6a8b94d67c"
    const val idForRecord2: String = "603e91da81087a6a8b94d67c"
    const val idForRecord3: String = "603e91da81087a6a8b94d67c"

    val record = Record(
        "2021-02-26T09:25:23.112+0000",
        "09:25:23.112"
    )

    val newRecord = Record(
        "2100-12-31T23:59:59.001+0000",
        "00:00:00.001"
    )
    const val newRecordId: String = "63e97450866664b10999zz9"

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