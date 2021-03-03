package com.carlosjimz87.stopwatch.data.api

import com.carlosjimz87.stopwatch.data.models.CreateResponse
import com.carlosjimz87.stopwatch.data.models.DeleteResponse
import com.carlosjimz87.stopwatch.data.models.ListResponse
import com.carlosjimz87.stopwatch.data.models.RecordResponse
import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.utils.Constants
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private val retrofit= Retrofit.Builder()
    .baseUrl(Constants.FAKE_API_BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    .build()

interface RecordsApiService {

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


/*
To avoid excessive use of resources
we create a Singleton to call the Api
using this JsonBinApi
*/
object RecordsApi {

    val retrofitService: RecordsApiService by lazy {
        retrofit.create(RecordsApiService::class.java)
    }

}