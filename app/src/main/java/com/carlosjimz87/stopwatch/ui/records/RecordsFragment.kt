package com.carlosjimz87.stopwatch.ui.records

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.carlosjimz87.stopwatch.MainActivity
import com.carlosjimz87.stopwatch.R
import com.carlosjimz87.stopwatch.databinding.RecordsFragmentBinding
import com.carlosjimz87.stopwatch.domain.viewmodels.RecordsViewModel
import com.carlosjimz87.stopwatch.domain.viewmodels.RecordsViewModelFactory
import com.carlosjimz87.stopwatch.domain.viewmodels.StopWatchViewModel
import com.carlosjimz87.stopwatch.utils.LifecycleManagedCoroutineScope
import com.carlosjimz87.stopwatch.utils.ManagedCoroutineScope

class RecordsFragment: Fragment() {
    companion object {
        fun newInstance() = RecordsFragment()
    }

    private lateinit var recordsViewModel: RecordsViewModel
    private lateinit var binding: RecordsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.records_fragment, container, false
        )
        // obtaining recordsViewModel from Provider
        recordsViewModel = ViewModelProvider(this,
            RecordsViewModelFactory(
                LifecycleManagedCoroutineScope(
                    lifecycleScope,
                    lifecycleScope.coroutineContext
                )
            )

            ).get(RecordsViewModel::class.java)


        setupRecyclerView(binding)

        return binding.root
    }

    private fun setupRecyclerView(binding: RecordsFragmentBinding) {
//        binding.recordsRecyclerView.layoutManager = LinearLayoutManager(context)
//        binding.recordsRecyclerView.adapter = RecordsAdapter(dummyRecords)
    }
}
