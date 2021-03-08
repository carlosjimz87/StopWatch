package com.carlosjimz87.stopwatch.domain.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carlosjimz87.stopwatch.databinding.RecordItemBinding
import com.carlosjimz87.stopwatch.domain.models.Record
import com.carlosjimz87.stopwatch.utils.Extensions.formatRecordDate
import com.carlosjimz87.stopwatch.utils.Extensions.unique

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Record>?) {
    val adapter = recyclerView.adapter as RecordsAdapter
    adapter.submitList(data)
}

class RecordsAdapter :
    ListAdapter<Record, RecordsAdapter.RecordsViewHolder>(DiffCallback) {
    init {
        this.setHasStableIds(true)
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Record>() {
        override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
            return oldItem === newItem
        }

        /**
         * Create new [RecyclerView] item views (invoked by the layout manager)
         */
        override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun getItemId(position: Int): Long {
        return this.currentList[position].unique()
    }

    class RecordsViewHolder(private val binding: RecordItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(record: Record) {
            binding.record = record
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): RecordsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecordItemBinding.inflate(layoutInflater, parent, false)
                return RecordsViewHolder(
                    binding
                )
            }
        }

    }

    override fun onBindViewHolder(holder: RecordsViewHolder, position: Int) {

        val record = getItem(position)
        val newRecord = Record(
            id = record.id,
            datetime = record.datetime.formatRecordDate(),
            time = record.time,
        )
        holder.bind(newRecord)
    }


    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecordsViewHolder {

        return RecordsViewHolder.from(parent)

    }


}
