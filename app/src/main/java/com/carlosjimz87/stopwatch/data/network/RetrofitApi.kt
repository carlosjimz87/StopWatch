package com.carlosjimz87.stopwatch.data.network

import com.carlosjimz87.stopwatch.data.models.CreateResponse
import com.carlosjimz87.stopwatch.data.models.DeleteResponse
import com.carlosjimz87.stopwatch.data.models.ListResponse
import com.carlosjimz87.stopwatch.data.models.RecordResponse
import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.utils.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private val retrofit= Retrofit.Builder()
    .baseUrl(Constants.POSTMAN_API_BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    .build()

interface RetrofitService {

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

object RetrofitApi {

    val service: RetrofitService by lazy {
        retrofit.create(RetrofitService::class.java)
    }

}