package com.carlosjimz87.stopwatch.data.db

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.utils.Constants.SHARED_PREFS_KEY
import com.google.gson.Gson

object SharedPrefsManager {

    suspend fun getRecords(context: Context): List<Record> {
        val prefs = context.getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE)
        val jsonRecords = prefs.getString(SHARED_PREFS_KEY, "[]")
        val gson = Gson()
        return gson.fromJson(jsonRecords, Array<Record>::class.java).toList()
    }

    suspend fun setRecords(context:Context, records:List<Record>){
        val prefs = context.getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE)
        val editor = prefs.edit()
        val gson = Gson()
        val jsonRecords = gson.toJson(records)
        editor.putString(SHARED_PREFS_KEY,jsonRecords)
        editor.apply()
    }
}