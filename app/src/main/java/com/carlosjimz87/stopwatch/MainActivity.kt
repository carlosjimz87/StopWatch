package com.carlosjimz87.stopwatch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.carlosjimz87.stopwatch.databinding.ActivityMainBinding
import com.carlosjimz87.stopwatch.ui.records.RecordsFragment
import com.carlosjimz87.stopwatch.ui.stopwatch.StopWatchFragment

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
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
                .replace(R.id.container, StopWatchFragment())
                .commitNow()
        }
    }
    private fun showRecordsFragment(savedInstanceState:Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container2, RecordsFragment())
                .commitNow()
        }
    }

}