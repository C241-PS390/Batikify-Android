package com.android.example.batikify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.example.batikify.R
import com.android.example.batikify.data.response.DataItemEncyclopedia
import com.android.example.batikify.databinding.ItemEncyclopediaBinding
import com.bumptech.glide.Glide

class EncyclopediaAdapter(private var encyclopediaList: List<DataItemEncyclopedia>) : RecyclerView.Adapter<EncyclopediaAdapter.EncyclopediaViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener : OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EncyclopediaViewHolder {
        val binding = ItemEncyclopediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EncyclopediaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EncyclopediaViewHolder, position: Int) {
        val user = encyclopediaList[position]
        holder.bind(user)
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return encyclopediaList.size
    }

    class EncyclopediaViewHolder(private val binding: ItemEncyclopediaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(encyclopedia: DataItemEncyclopedia) {
            binding.tvEncyclopediaTitle.text = encyclopedia.name
            binding.tvEncyclopediaDescription.text = encyclopedia.description
            binding.tvBatikComeFrom.text = encyclopedia.origin
            Glide.with(itemView.context)
                .load(encyclopedia.imageUrl)
                .placeholder(R.drawable.baseline_downloading_24)
                .error(R.drawable.baseline_error_24)
                .into(binding.ivBatikImage)
        }
    }
}