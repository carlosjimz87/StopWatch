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
import com.carlosjimz87.stopwatch.domain.stopwatch.StopWatch.*

class StopWatchFragment : Fragment() {
    companion object {
        fun newInstance() = StopWatchFragment()
    }

    private lateinit var viewModel: StopWatchViewModel
    private lateinit var binding: StopwatchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.stopwatch_fragment, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StopWatchViewModel::class.java)

        binding.stopBtn.setOnClickListener { viewModel.resetTimer() }

        binding.startOrPauseBtn.setOnClickListener {
            when (viewModel.startStopButtonState.value) {
                STATES.RESUME ->  viewModel.startTimer()
                STATES.START -> viewModel.startTimer()
                STATES.PAUSE -> viewModel.stopTimer()
                else -> {}
            }
        }

        viewModel.startStopButtonState.observe(viewLifecycleOwner, { state ->

            when (state) {
                STATES.START -> changeStartOrPauseBtn(R.string.start,R.color.green)
                STATES.PAUSE -> changeStartOrPauseBtn(R.string.pause,R.color.purple)
                STATES.RESUME -> changeStartOrPauseBtn(R.string.resume,R.color.green)
                else -> {}
            }
        })

        viewModel.formattedTime.observe(viewLifecycleOwner, { time ->
            binding.textViewStopWatch.text = time
        })

    }

    private fun changeStartOrPauseBtn(text:Int,color:Int) {
        binding.startOrPauseBtn.setText(text)
        binding.startOrPauseBtn.setBackgroundColor(getColor(color))
    }

    private fun getColor(resource: Int): Int {
        return ContextCompat.getColor(context!!, resource)
    }
}