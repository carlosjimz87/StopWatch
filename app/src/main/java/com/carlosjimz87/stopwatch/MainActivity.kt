package com.carlosjimz87.stopwatch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.carlosjimz87.stopwatch.databinding.ActivityMainBinding
import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.ui.records.RecordsFragment
import com.carlosjimz87.stopwatch.ui.watch.WatchFragment

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
                .replace(R.id.container, WatchFragment.newInstance())
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