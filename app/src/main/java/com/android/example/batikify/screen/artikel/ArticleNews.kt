package com.android.example.batikify.screen.artikel

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.batikify.adapter.ArticleAdapter
import com.android.example.batikify.data.response.DataItemNews
import com.android.example.batikify.databinding.ActivityArticleBinding
import com.android.example.batikify.factory.ViewModelFactory

class ArticleNews:  AppCompatActivity() {

    private val articleViewModel by viewModels<ArticleViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var binding : ActivityArticleBinding

    private lateinit var articleAdapter : ArticleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvNewsItem.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvNewsItem.addItemDecoration(itemDecoration)

        articleViewModel.listNews.observe(this){ articleList ->
            showArticleList(articleList)
        }

        articleViewModel.isLoading.observe(this){
            showLoading(it)
        }
    }
    private fun showArticleList(articleList : List<DataItemNews>) {
        articleAdapter = ArticleAdapter(articleList)
        binding.rvNewsItem.adapter = articleAdapter

        articleAdapter.setOnItemClickListener(object : ArticleAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val article = articleList[position]
                val url = article.link
                val articleWeb = Intent(Intent.ACTION_VIEW)
                articleWeb.setData(Uri.parse(url))
                startActivity(articleWeb)
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}