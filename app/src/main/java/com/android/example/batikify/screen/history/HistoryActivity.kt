package com.android.example.batikify.screen.history

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.batikify.adapter.EncyclopediaAdapter
import com.android.example.batikify.adapter.HistoryAdapter
import com.android.example.batikify.data.response.DataItemEncyclopedia
import com.android.example.batikify.data.response.DataItemHistory
import com.android.example.batikify.databinding.ActivityEncyclopediaBinding
import com.android.example.batikify.databinding.ActivityHistoryBinding
import com.android.example.batikify.factory.ViewModelFactory
import com.android.example.batikify.screen.detail.DetailActivity

class HistoryActivity:  AppCompatActivity() {

    private val historyViewModel by viewModels<HistoryViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var binding : ActivityHistoryBinding

    private lateinit var historyAdapter : HistoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvHistory.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvHistory.addItemDecoration(itemDecoration)

        historyViewModel.listHistory.observe(this){ historyList ->
            showHistoryList(historyList)
        }

        historyViewModel.isLoading.observe(this){
            showLoading(it)
        }
    }
    private fun showHistoryList(historyList : List<DataItemHistory>) {
        historyAdapter = HistoryAdapter(historyList)
        binding.rvHistory.adapter = historyAdapter

        historyAdapter.setOnItemClickListener(object : HistoryAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val history = historyList[position]
                val detailPage = Intent(this@HistoryActivity, DetailActivity::class.java)
                detailPage.putExtra("HISTORY_ID",history.id)
                startActivity(detailPage)
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