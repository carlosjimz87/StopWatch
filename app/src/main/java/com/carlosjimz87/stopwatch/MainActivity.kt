package com.carlosjimz87.stopwatch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.carlosjimz87.stopwatch.databinding.ActivityMainBinding
import com.carlosjimz87.stopwatch.domain.viewmodels.RecordsViewModel
import com.carlosjimz87.stopwatch.domain.viewmodels.StopWatchViewModel
import com.carlosjimz87.stopwatch.ui.records.RecordsFragment
import com.carlosjimz87.stopwatch.ui.stopwatch.StopWatchFragment
import com.carlosjimz87.stopwatch.utils.Extensions.isEmpty
import com.carlosjimz87.stopwatch.utils.Extensions.isTimeEmpty

class MainActivity : AppCompatActivity() {

    lateinit var recordsViewModel: RecordsViewModel
    lateinit var stopWatchViewModel: StopWatchViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideToolbar()
        // obtaining recordsViewModel from Provider
        recordsViewModel = ViewModelProvider(this).get(RecordsViewModel::class.java)
        // obtaining stopWatchViewModel from Provider
        stopWatchViewModel = ViewModelProvider(this).get(StopWatchViewModel::class.java)


        stopWatchViewModel.saveRecord.observe(this, { newRecord ->
            if(!newRecord.isEmpty() && !stopWatchViewModel.formattedTime.value?.isTimeEmpty()!!)
                recordsViewModel.addRecord(newRecord)
        })


        showWatchFragment(savedInstanceState)
        showRecordsFragment(savedInstanceState)


    }


    private fun hideToolbar(){
        supportActionBar?.hide()
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