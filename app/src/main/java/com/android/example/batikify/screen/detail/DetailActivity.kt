package com.android.example.batikify.screen.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.example.batikify.R
import com.android.example.batikify.databinding.ActivityDetailBatikBinding
import com.android.example.batikify.factory.ViewModelFactory
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBatikBinding

    private val detailViewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBatikBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val id = intent.getStringExtra("BATIK_ID")
        val historyId = intent.getStringExtra("HISTORY_ID")

        if(id != null && historyId == null){
            detailViewModel.displayById(id.toString())
        }else if(historyId != null && id == null) {
            detailViewModel.displayByHistory(historyId.toString())
        }

        setupAction()

    }

    private fun setupAction(){

        detailViewModel.isLoading.observe(this){
            showLoading(it)
        }

        detailViewModel.name.observe(this){name ->
            binding.tvNameMotif.text = name
        }

        detailViewModel.origin.observe(this){origin ->
            val asal = "asal "
            binding.tvDetailOrigin.text = asal + origin
        }


        detailViewModel.description.observe(this){ description ->
            binding.tvDescriptionBatik.text = description
        }

        detailViewModel.imageUrl.observe(this){imageUrl ->
            Glide.with(this)
                .load(imageUrl)
                .error(R.drawable.baseline_error_24)
                .into(binding.ivImageBatik)
        }
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object{
        private val TAG = "DetailActivity"
    }
}