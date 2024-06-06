package com.android.example.batikify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.example.batikify.R
import com.android.example.batikify.data.response.DataItemEncyclopedia
import com.android.example.batikify.databinding.ItemEnsyclopediaBinding
import com.bumptech.glide.Glide

class EnsiklopediaAdapter : PagingDataAdapter<DataItemEncyclopedia, EnsiklopediaAdapter.EnsiklopediaViewHolder>(DIFF_CALLBACK){

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener : OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnsiklopediaViewHolder {
        val binding = ItemEnsyclopediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EnsiklopediaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EnsiklopediaViewHolder, position: Int) {
        val ensiklopedia = getItem(position)
        if (ensiklopedia != null) {
            holder.bind(ensiklopedia)
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }

    class EnsiklopediaViewHolder(private val binding: ItemEnsyclopediaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ensiklopedia : DataItemEncyclopedia) {
            Glide.with(itemView.context)
                .load(ensiklopedia.photoUrl)
                .placeholder(R.drawable.baseline_downloading_24)
                .error(R.drawable.baseline_error_24)
                .into(binding.ivBatikImage)
            binding.tvEncyclopediaTitle.text = ensiklopedia.name
            binding.tvBatikComeFrom.text = ensiklopedia.origin
            binding.tvEncyclopediaDescription.text = ensiklopedia.description
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItemEncyclopedia>() {
            override fun areItemsTheSame(oldItem: DataItemEncyclopedia, newItem: DataItemEncyclopedia): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItemEncyclopedia, newItem: DataItemEncyclopedia): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}