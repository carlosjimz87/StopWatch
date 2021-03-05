package com.carlosjimz87.stopwatch.data.network

import com.carlosjimz87.stopwatch.data.models.CreateResponse
import com.carlosjimz87.stopwatch.data.models.DeleteResponse
import com.carlosjimz87.stopwatch.data.models.ListResponse
import com.carlosjimz87.stopwatch.data.models.RecordResponse
import com.carlosjimz87.stopwatch.data.network.RetrofitApi.BASE_URL
import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.utils.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private val retrofit= Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    .build()

interface RetrofitService {

    @GET(value = "/e/collection/{collectionId}/all-bins")
    suspend fun listRecords(
        @Path("collectionId") collectionId: String,
        @HeaderMap headers: Map<String,String>

    ): ListResponse

    @GET(value = "/b/{recordId}")
    suspend fun getRecord(
        @Path("recordId") recordId: String,
        @HeaderMap headers: Map<String,String>
    ): RecordResponse


    @POST(value = "/b")
    suspend fun createRecord(
        @Body record: Record,
        @HeaderMap headers: Map<String,String>
    ): CreateResponse

    @DELETE(value = "/b/{recordId}")
    suspend fun deleteRecord(
        @Path("recordId") recordId: String,
        @HeaderMap headers: Map<String,String>
    ): DeleteResponse

}

object RetrofitApi {
    var BASE_URL = Constants.API_BASE_URL
    val service: RetrofitService by lazy {
        retrofit.create(RetrofitService::class.java)
    }

    fun headers(): Map<String, String> {
        val headerMap = mutableMapOf<String, String>()
        headerMap["private"] = false.toString()
        headerMap["secret-key"] = Constants.API_KEY_VALUE
        headerMap["collection-id"] = Constants.COLLECTION_ID
        headerMap["Content-Type"] = "application/json"
        return headerMap
    }

}