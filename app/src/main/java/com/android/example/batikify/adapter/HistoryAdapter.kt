package com.android.example.batikify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.example.batikify.R
import com.android.example.batikify.data.response.DataItemHistory
import com.android.example.batikify.databinding.ItemHistoryBinding
import com.bumptech.glide.Glide

class HistoryAdapter(private var historyList: List<DataItemHistory>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener : OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val user = historyList[position]
        holder.bind(user)
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    class HistoryViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(history: DataItemHistory) {
            binding.tvHistoryTitle.text = history.result
            binding.tvHistoryDescription.text = history.description
            binding.tvHistoryOrigin.text = history.origin
            Glide.with(itemView.context)
                .load(history.photoUrl)
                .placeholder(R.drawable.baseline_downloading_24)
                .error(R.drawable.baseline_error_24)
                .into(binding.ivHistoryImage)
        }
    }
}