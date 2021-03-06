package com.carlosjimz87.stopwatch.ui.records

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.RecyclerView
import com.carlosjimz87.stopwatch.MainActivity
import com.carlosjimz87.stopwatch.R
import com.carlosjimz87.stopwatch.databinding.RecordsFragmentBinding
import com.carlosjimz87.stopwatch.domain.adapters.RecordsAdapter
import com.carlosjimz87.stopwatch.domain.viewmodels.RecordsViewModel
import com.carlosjimz87.stopwatch.utils.Extensions.isEmpty
import androidx.recyclerview.widget.SimpleItemAnimator

import androidx.recyclerview.widget.RecyclerView.ItemAnimator

import android.R.attr.name




class RecordsFragment: Fragment() {

    private lateinit var recordsViewModel: RecordsViewModel
    private lateinit var binding: RecordsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.records_fragment, container, false
        )

        recordsViewModel = (activity as MainActivity).recordsViewModel

        // Allows Data Binding to Observe LiveData
        binding.lifecycleOwner = this
        // Giving the binding access to the RecordsViewModel
        binding.recordsViewModel = recordsViewModel

        setupRecyclerView()
        attachSwipeGestureToRecyclerView()
        setupStates()
        return binding.root
    }


    override fun onResume() {
        super.onResume()
        recordsViewModel.updateRecords()
    }

    private fun setupStates(){
        recordsViewModel.status.observe(viewLifecycleOwner, {
            binding.recordsRecyclerView.scheduleLayoutAnimation()
        })
    }

    private fun setupRecyclerView() {
        binding.recordsRecyclerView.adapter = RecordsAdapter()
        (binding.recordsRecyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }

    private fun attachSwipeGestureToRecyclerView(){
        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.recordsRecyclerView)
    }

    private var simpleCallback = object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT),
        ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)
    ){

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder,
        ): Boolean {
           return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            val pos = viewHolder.adapterPosition

            when(direction){
                ItemTouchHelper.LEFT->{
                    recordsViewModel.deleteRecord(pos)
                }
                ItemTouchHelper.RIGHT->{
                    recordsViewModel.deleteRecord(pos)
                }
            }
        }
    }
}
