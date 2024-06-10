package com.android.example.batikify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.example.batikify.R
import com.android.example.batikify.data.response.DataItemHistory
import com.android.example.batikify.data.response.DataItemNews
import com.android.example.batikify.databinding.ItemArticleSliderBinding
import com.android.example.batikify.databinding.ItemHistoryBinding
import com.bumptech.glide.Glide

class ArticleAdapter(private var articleList: List<DataItemNews>) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener : OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticleSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val user = articleList[position]
        holder.bind(user)
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    class ArticleViewHolder(private val binding: ItemArticleSliderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: DataItemNews) {
            binding.tvArticleTitle.text = article.title
            binding.tvArticleDescription.text = article.snippet
            binding.tvDate.setText(article.date)
            binding.tvSource.setText(article.source)
            Glide.with(itemView.context)
                .load(article.image)
                .placeholder(R.drawable.baseline_downloading_24)
                .error(R.drawable.baseline_error_24)
                .into(binding.ivArticleImage)
        }
    }
}