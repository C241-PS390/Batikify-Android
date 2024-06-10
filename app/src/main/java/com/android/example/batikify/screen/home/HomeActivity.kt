package com.android.example.batikify.screen.home

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.batikify.R
import com.android.example.batikify.adapter.ArticleAdapter
import com.android.example.batikify.adapter.EncyclopediaAdapter
import com.android.example.batikify.adapter.HistoryAdapter
import com.android.example.batikify.data.response.DataItemEncyclopedia
import com.android.example.batikify.data.response.DataItemHistory
import com.android.example.batikify.data.response.DataItemNews
import com.android.example.batikify.databinding.ActivityHomeBinding
import com.android.example.batikify.factory.ViewModelFactory
import com.android.example.batikify.screen.artikel.ArtikelActivity
import com.android.example.batikify.screen.classification.ClassificationActivity
import com.android.example.batikify.screen.detail.DetailActivity
import com.android.example.batikify.screen.ensiklopedia.EnsiklopediaActivity
import com.android.example.batikify.screen.main.MainActivity
import com.android.example.batikify.screen.profile.ProfileActivity

class HomeActivity: AppCompatActivity(){

    private lateinit var binding : ActivityHomeBinding
    private val homeViewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var historyAdapter : HistoryAdapter
    private lateinit var articleAdapter : ArticleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        homeViewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this@HomeActivity, MainActivity::class.java))
                finish()
            }
        }

        binding.classificationButton.setOnClickListener {
            val intent = Intent(this@HomeActivity,ClassificationActivity::class.java)
            startActivity(intent)
        }

        binding.ensyclopeidaButton.setOnClickListener {
            val intent = Intent(this@HomeActivity,EnsiklopediaActivity::class.java)
            startActivity(intent)
        }

        homeViewModel.listHistory.observe(this){ historyList ->
            showHistoryList(historyList)
        }

        homeViewModel.listNews.observe(this){ articleList ->
            showArticleList(articleList)
        }
    }

    private fun showHistoryList(historyList : List<DataItemHistory>) {
        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.rvHistory.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvHistory.addItemDecoration(itemDecoration)

        historyAdapter = HistoryAdapter(historyList)
        binding.rvHistory.adapter = historyAdapter

        historyAdapter.setOnItemClickListener(object : HistoryAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val history = historyList[position]
                val detailPage = Intent(this@HomeActivity, DetailActivity::class.java)
                detailPage.putExtra("HISTORY_ID",history.id)
                startActivity(detailPage)
            }
        })
    }

    private fun showArticleList(articleList : List<DataItemNews>) {
        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.rvNews.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvNews.addItemDecoration(itemDecoration)

        articleAdapter = ArticleAdapter(articleList)
        binding.rvNews.adapter = articleAdapter

        articleAdapter.setOnItemClickListener(object : ArticleAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val article = articleList[position]
                val detailPage = Intent(this@HomeActivity, ArtikelActivity::class.java)
//                detailPage.putExtra("BATIK_ID",ensiklopedia.id)
                startActivity(detailPage)
            }
        })
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