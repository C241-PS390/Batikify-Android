package com.android.example.batikify.screen.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.android.example.batikify.databinding.ActivityDetailBatikBinding
import com.android.example.batikify.factory.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBatikBinding

    private val detailViewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBatikBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("BATIK_ID")

        detailViewModel.display(id.toString())

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
            val asal = "asal"
            binding.tvDetailOrigin.text = asal + origin
        }


        detailViewModel.description.observe(this){ description ->
            binding.tvDescriptionBatik.text = description
        }

//        detailViewModel.photoUrl.observe(this){photoUrl ->
//            Glide.with(this)
//                .load(photoUrl)
//                .error(R.drawable.baseline_error_24)
//                .into(binding.ivGambarBatik)
//        }
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object{
        private val TAG = "DetailActivity"
    }
}