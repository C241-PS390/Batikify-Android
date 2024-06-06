package com.android.example.batikify.screen.ensiklopedia

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.batikify.adapter.EnsiklopediaAdapter
import com.android.example.batikify.adapter.LoadingStateAdapter
import com.android.example.batikify.databinding.ActivityEnsiklopediaBinding
import com.android.example.batikify.screen.detail.DetailActivity

class EnsiklopediaActivity : AppCompatActivity(){

    private lateinit var binding : ActivityEnsiklopediaBinding
    
    private lateinit var ensiklopediaAdapter: EnsiklopediaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnsiklopediaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    private fun showStoryList() {
        ensiklopediaAdapter = EnsiklopediaAdapter()
        binding.rvEnsyclopedia.apply {
            layoutManager = LinearLayoutManager(this@EnsiklopediaActivity)
            addItemDecoration(DividerItemDecoration(this@EnsiklopediaActivity, LinearLayoutManager.VERTICAL))
            adapter = ensiklopediaAdapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    ensiklopediaAdapter.retry()
                })
        }

        ensiklopediaAdapter.setOnItemClickListener(object : EnsiklopediaAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val ensiklopedia = ensiklopediaAdapter.snapshot()[position]
                if (ensiklopedia != null) {
                    val detailPage = Intent(this@EnsiklopediaActivity, DetailActivity::class.java)
                    detailPage.putExtra("STORY_ID", ensiklopedia.id)
                    startActivity(detailPage)
                }
            }
        })
    }
}