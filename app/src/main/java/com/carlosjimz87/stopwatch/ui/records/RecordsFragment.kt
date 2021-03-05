package com.carlosjimz87.stopwatch.ui.records

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.carlosjimz87.stopwatch.MainActivity
import com.carlosjimz87.stopwatch.R
import com.carlosjimz87.stopwatch.databinding.RecordsFragmentBinding
import com.carlosjimz87.stopwatch.domain.adapters.RecordsAdapter
import com.carlosjimz87.stopwatch.domain.viewmodels.RecordsViewModel
import com.carlosjimz87.stopwatch.utils.Extensions.isEmpty

class RecordsFragment: Fragment() {

    private lateinit var recordsViewModel: RecordsViewModel
    private lateinit var binding: RecordsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.records_fragment, container, false
        )

        recordsViewModel = (activity as MainActivity).recordsViewModel

        // Allows Data Binding to Observe LiveData
        binding.lifecycleOwner = this
        // Giving the binding access to the RecordsViewModel
        binding.recordsViewModel = recordsViewModel

        setupRecyclerView(binding)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        recordsViewModel.updateRecords()
    }

    private fun setupRecyclerView(binding: RecordsFragmentBinding) {
        binding.recordsRecyclerView.adapter = RecordsAdapter()
    }
}
