package com.carlosjimz87.stopwatch.domain.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carlosjimz87.stopwatch.domain.models.Record

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Record>?) {
    val adapter = recyclerView.adapter as RecordsAdapter
    adapter.submitList(data)
}