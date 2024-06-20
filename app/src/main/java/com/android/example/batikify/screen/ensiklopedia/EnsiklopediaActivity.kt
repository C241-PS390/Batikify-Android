package com.android.example.batikify.screen.ensiklopedia

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.batikify.R
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
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        binding.rvEncyclopedia.layoutManager = layoutManager

        ensiklopediaViewModel.listEncyclopedia.observe(this){ encyclopediaList ->
            showEncyclopediaList(encyclopediaList)
        }

        ensiklopediaViewModel.isLoading.observe(this){
            showLoading(it)
        }

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.hint = searchView.text
                    searchView.hide()
                    ensiklopediaViewModel.searchEncyclopedia(searchView.text.toString())
                    false
                }
        }
    }
    private fun showEncyclopediaList(encyclopediaList : List<DataItemEncyclopedia>) {
        ensiklopediaAdapter = EncyclopediaAdapter(encyclopediaList)
        binding.rvEncyclopedia.adapter = ensiklopediaAdapter

        ensiklopediaAdapter.setOnItemClickListener(object : EncyclopediaAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val ensiklopedia = encyclopediaList[position]
                val detailPage = Intent(this@EnsiklopediaActivity, DetailActivity::class.java)
                detailPage.putExtra("BATIK_ID",ensiklopedia.id)
                startActivity(detailPage)
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}