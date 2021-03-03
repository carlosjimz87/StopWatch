package com.carlosjimz87.stopwatch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.carlosjimz87.stopwatch.data.api.RecordsApi
import com.carlosjimz87.stopwatch.databinding.ActivityMainBinding
import com.carlosjimz87.stopwatch.ui.records.RecordsFragment
import com.carlosjimz87.stopwatch.ui.stopwatch.StopWatchFragment
import com.carlosjimz87.stopwatch.utils.Constants.API_KEY_VALUE
import com.carlosjimz87.stopwatch.utils.Constants.COLLECTION_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideToolbar()
        showWatchFragment(savedInstanceState)
        showRecordsFragment(savedInstanceState)

    }


    private fun hideToolbar(){
        supportActionBar?.hide();
    }

    private fun showWatchFragment(savedInstanceState:Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, StopWatchFragment.newInstance())
                .commitNow()
        }
    }
    private fun showRecordsFragment(savedInstanceState:Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container2, RecordsFragment.newInstance())
                .commitNow()
        }
    }

}