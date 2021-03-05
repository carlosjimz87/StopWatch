package com.carlosjimz87.stopwatch.ui.stopwatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.carlosjimz87.stopwatch.R
import com.carlosjimz87.stopwatch.databinding.StopwatchFragmentBinding
import com.carlosjimz87.stopwatch.domain.viewmodels.StopWatchViewModel

class StopWatchFragment : Fragment() {
    companion object {
        fun newInstance() = StopWatchFragment()
    }

    private lateinit var stopWatchViewModel: StopWatchViewModel
    private lateinit var binding: StopwatchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.stopwatch_fragment, container, false
        )
        // obtaining stopWatchViewModel from Provider
        stopWatchViewModel = ViewModelProvider(this).get(StopWatchViewModel::class.java)

        // Allows Data Binding to Observe LiveData
        binding.lifecycleOwner = this
        // Giving the binding access to the StopWatchViewModel
        binding.viewModel = stopWatchViewModel

        setupListeners()
        setupObservers()

        return binding.root
    }

    private fun setupObservers() {

        stopWatchViewModel.state.observe(viewLifecycleOwner, { state ->

            when (state) {
                StopWatchViewModel.STATES.START -> changeStartOrPauseBtn(R.string.start, R.color.green)
                StopWatchViewModel.STATES.PAUSE -> changeStartOrPauseBtn(R.string.pause, R.color.purple)
                StopWatchViewModel.STATES.RESUME -> changeStartOrPauseBtn(R.string.resume, R.color.green)
                else -> {
                }
            }
        })

//        stopWatchModel.formattedTime.observe(viewLifecycleOwner, { time ->
//            binding.textViewStopWatch.text = time
//            if(stopWatchModel.saveRecord){
//                (activity as MainActivity).recordsViewModel.addRecord(time)
//            }
//        })
    }

    private fun setupListeners() {

//        binding.stopBtn.setOnClickListener { stopWatchModel.resetTimer() }

//        binding.startOrPauseBtn.setOnClickListener {
//            when (stopWatchModel.startStopButtonState.value) {
//                STATES.RESUME -> stopWatchModel.startTimer()
//                STATES.START -> stopWatchModel.startTimer()
//                STATES.PAUSE -> stopWatchModel.stopTimer()
//                else -> {
//                }
//            }
//        }
    }

    private fun changeStartOrPauseBtn(text: Int, color: Int) {
        binding.startOrPauseBtn.setText(text)
        binding.startOrPauseBtn.setBackgroundColor(getColor(color))
    }

    private fun getColor(resource: Int): Int {
        return ContextCompat.getColor(context!!, resource)
    }
}