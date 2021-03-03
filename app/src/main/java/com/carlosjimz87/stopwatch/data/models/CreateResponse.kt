package com.carlosjimz87.stopwatch.data.models


data class CreateResponse(
    val collectionID: String,
    val data: RecordResponse,
    val id: String,
    val private: Boolean,
    val success: Boolean
)