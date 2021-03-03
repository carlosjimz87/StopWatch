package com.carlosjimz87.stopwatch.data.api

import com.carlosjimz87.stopwatch.data.models.CreateResponse
import com.carlosjimz87.stopwatch.data.models.DeleteResponse
import com.carlosjimz87.stopwatch.data.models.ListResponse
import com.carlosjimz87.stopwatch.data.models.RecordResponse
import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.utils.Constants
import retrofit2.http.*

interface ApiService {

    @GET(value = "/e/collection/{collectionId}/all-bins")
    suspend fun listRecords(
        @Path("collectionId") collectionId: String,
        @Header(Constants.API_KEY_NAME) secret: String
    ): ListResponse

    @GET(value = "/b/{recordId}")
    suspend fun getRecord(
        @Path("recordId") recordId: String,
        @Header(Constants.API_KEY_NAME) secret: String
    ): RecordResponse

    @POST(value = "/b")
    suspend fun createRecord(
        @Body record: Record,
        @Header(Constants.API_KEY_NAME) secret: String
    ): CreateResponse

    @GET(value = "/b/{recordId}")
    suspend fun deleteRecord(
        @Path("recordId") recordId: String,
        @Header(Constants.API_KEY_NAME) secret: String
    ): DeleteResponse

}