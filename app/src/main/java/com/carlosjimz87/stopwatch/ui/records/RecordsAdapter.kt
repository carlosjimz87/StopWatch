package com.carlosjimz87.stopwatch.ui.records

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.carlosjimz87.stopwatch.R
import com.carlosjimz87.stopwatch.databinding.RecordItemBinding

import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.utils.Formatter


class RecordsAdapter(private val records: List<Record>) :
    RecyclerView.Adapter<RecordsAdapter.RecordsHolder>() {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordsHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding: RecordItemBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.record_item, parent, false
        )

        context = parent.context
        return RecordsHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordsHolder, position: Int) {
        val record: Record = records[position]
        val formattedDate = Formatter.formatRecordDate(
            context.resources.getString(R.string.dateAtText, record.datetime)
        )
        holder.binding.dateAt.text = formattedDate
        holder.binding.time.text = record.time

    }

    override fun getItemCount(): Int = records.size

    class RecordsHolder(val binding: RecordItemBinding) : RecyclerView.ViewHolder(
        binding.root
    )

}
