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

class RecordsFragment: Fragment() {


    private var dummyRecords: List<Record> = listOf(
        Record("2021-03-02T18:36:59.123+0000","00:00:31.234"),
        Record("2021-03-02T09:35:22.354+0000","00:00:11.236"),
        Record("2021-03-01T11:22:63.232+0000","00:00:15.159"),
        Record("2021-03-01T09:55:33.231+0000","00:00:15.392"),
        Record("2021-02-28T10:24:21.123+0000","00:00:16.101"),
        Record("2021-02-26T13:15:50.390+0000","00:00:23.089"),
    )


    companion object {
        fun newInstance() = RecordsFragment()
    }

    private lateinit var viewModel: RecordsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: RecordsFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.records_fragment, container, false)
        initRecycler(binding)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecordsViewModel::class.java)
    }
    private fun initRecycler(binding: RecordsFragmentBinding) {
        binding.recordsRecyclerView.layoutManager = GridLayoutManager(context,1)
        binding.recordsRecyclerView.adapter = RecordsAdapter(dummyRecords)

    }
}