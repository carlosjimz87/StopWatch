package com.carlosjimz87.stopwatch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.carlosjimz87.stopwatch.data.api.ApiService
import com.carlosjimz87.stopwatch.databinding.ActivityMainBinding
import com.carlosjimz87.stopwatch.ui.watch.WatchFragment
import com.carlosjimz87.stopwatch.utils.Constants
import com.carlosjimz87.stopwatch.utils.Constants.COLLECTION_ID
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideToolbar()
//        beginTransition(savedInstanceState)
        val service = Retrofit.Builder()
            .baseUrl(Constants.FAKE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getRecord(
                secret = Constants.API_KEY_VALUE,
                recordId = "603e91da81087a6a8b94d67c"
            )

            Log.d("ApisTest", "Response: $response")
        }
    }


    private fun hideToolbar(){
        supportActionBar?.hide();
    }

    private fun beginTransition(savedInstanceState:Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WatchFragment())
                .commitNow()
        }
    }
}