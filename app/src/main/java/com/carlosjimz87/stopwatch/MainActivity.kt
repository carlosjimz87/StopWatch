package com.carlosjimz87.stopwatch

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.carlosjimz87.stopwatch.databinding.ActivityMainBinding
import com.carlosjimz87.stopwatch.ui.watch.WatchFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideToolbar()
        beginTransition(savedInstanceState)
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