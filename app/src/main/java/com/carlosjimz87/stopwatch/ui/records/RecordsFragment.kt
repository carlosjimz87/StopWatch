package com.carlosjimz87.stopwatch.ui.records

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.carlosjimz87.stopwatch.R
import com.carlosjimz87.stopwatch.databinding.RecordsFragmentBinding
import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.domain.viewmodels.RecordsViewModel

class RecordsFragment: Fragment() {


    private var dummyRecords: List<Record> = listOf(
    )


    companion object {
        fun newInstance() = RecordsFragment()
    }

    private val viewModel by lazy { ViewModelProvider(this).get(RecordsViewModel::class.java)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: RecordsFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.records_fragment, container, false)
        initRecycler(binding)
        return binding.root
    }

    private fun initRecycler(binding: RecordsFragmentBinding) {
        binding.recordsRecyclerView.layoutManager = GridLayoutManager(context,1)
        binding.recordsRecyclerView.adapter = RecordsAdapter(dummyRecords)

    }
}