package com.android.example.batikify.screen.ensiklopedia

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.batikify.adapter.EncyclopediaAdapter

import com.android.example.batikify.data.response.DataItemEncyclopedia
import com.android.example.batikify.databinding.ActivityEncyclopediaBinding
import com.android.example.batikify.factory.ViewModelFactory
import com.android.example.batikify.screen.detail.DetailActivity

class EnsiklopediaActivity : AppCompatActivity(){

    private val ensiklopediaViewModel by viewModels<EnsiklopediaViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var binding : ActivityEncyclopediaBinding
    
    private lateinit var ensiklopediaAdapter: EncyclopediaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEncyclopediaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvEncyclopedia.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvEncyclopedia.addItemDecoration(itemDecoration)

        ensiklopediaViewModel.listEncyclopedia.observe(this){ encyclopediaList ->
            showEncyclopediaList(encyclopediaList)
        }

        ensiklopediaViewModel.isLoading.observe(this){
            showLoading(it)
        }
    }
    private fun showEncyclopediaList(encyclopediaList : List<DataItemEncyclopedia>) {
        ensiklopediaAdapter = EncyclopediaAdapter(encyclopediaList)
        binding.rvEncyclopedia.adapter = ensiklopediaAdapter

        ensiklopediaAdapter.setOnItemClickListener(object : EncyclopediaAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val ensiklopedia = encyclopediaList[position]
                val detailPage = Intent(this@EnsiklopediaActivity, DetailActivity::class.java)
                detailPage.putExtra("STORY_ID", ensiklopedia.id)
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