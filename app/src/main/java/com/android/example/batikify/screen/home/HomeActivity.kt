package com.android.example.batikify.screen.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.batikify.R
import com.android.example.batikify.adapter.ArticleCardAdapter
import com.android.example.batikify.adapter.HistoryAdapter
import com.android.example.batikify.data.response.DataItemHistory
import com.android.example.batikify.data.response.DataItemNews
import com.android.example.batikify.databinding.ActivityHomeBinding
import com.android.example.batikify.factory.ViewModelFactory
import com.android.example.batikify.screen.artikel.ArticleNews
import com.android.example.batikify.screen.classification.ClassificationActivity
import com.android.example.batikify.screen.detail.DetailActivity
import com.android.example.batikify.screen.ensiklopedia.EnsiklopediaActivity
import com.android.example.batikify.screen.history.HistoryActivity
import com.android.example.batikify.screen.main.MainActivity
import com.android.example.batikify.screen.profile.ProfileActivity

class HomeActivity: AppCompatActivity(){

    private lateinit var binding : ActivityHomeBinding
    private val homeViewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var historyAdapter : HistoryAdapter
    private lateinit var articleAdapter : ArticleCardAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonAction()

        homeViewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this@HomeActivity, MainActivity::class.java))
                finish()
            }
        }

        homeViewModel.listHistory.observe(this){ historyList ->
            showHistoryList(historyList)
        }

        homeViewModel.listNews.observe(this){ articleList ->
            showArticleList(articleList)
        }
    }

    private fun showHistoryList(historyList: List<DataItemHistory>?) {
        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.rvHistory.layoutManager = layoutManager

        historyAdapter = HistoryAdapter(historyList)
        binding.rvHistory.adapter = historyAdapter

        historyAdapter.setOnItemClickListener(object : HistoryAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val history = historyList?.get(position)
                val detailPage = Intent(this@HomeActivity, DetailActivity::class.java)
                detailPage.putExtra("HISTORY_ID", history?.id)
                startActivity(detailPage)
            }
        })
    }

    private fun showArticleList(articleList: List<DataItemNews>?) {
        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.rvNews.layoutManager = layoutManager

        articleAdapter = ArticleCardAdapter(articleList)
        binding.rvNews.adapter = articleAdapter

        articleAdapter.setOnItemClickListener(object : ArticleCardAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val article = articleList?.get(position)
                val url = article?.link
                val articleWeb = Intent(Intent.ACTION_VIEW)
                articleWeb.setData(Uri.parse(url))
                startActivity(articleWeb)
            }
        })
    }

    private fun buttonAction(){
        binding.classificationButton.setOnClickListener {
            val intent = Intent(this@HomeActivity,ClassificationActivity::class.java)
            startActivity(intent)
        }

        binding.ensyclopeidaButton.setOnClickListener {
            val intent = Intent(this@HomeActivity,EnsiklopediaActivity::class.java)
            startActivity(intent)
        }

        binding.nextHistory.setOnClickListener{
            val intent = Intent(this@HomeActivity,HistoryActivity::class.java)
            startActivity(intent)
        }

        binding.nextNews.setOnClickListener{
            val intent = Intent(this@HomeActivity,ArticleNews::class.java)
            startActivity(intent)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                val profilePage =  Intent(this@HomeActivity,ProfileActivity::class.java)
                startActivity(profilePage)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}