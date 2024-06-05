package com.android.example.batikify.screen.home

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.android.example.batikify.databinding.ActivityHomeBinding
import com.android.example.batikify.screen.classification.ClassificationActivity
import com.android.example.batikify.screen.ensiklopedia.EnsiklopediaActivity

class HomeActivity: AppCompatActivity(){

    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.classificationButton.setOnClickListener {
            val intent = Intent(this@HomeActivity,ClassificationActivity::class.java)
            startActivity(intent)
        }

        binding.ensyclopeidaButton.setOnClickListener {
            val intent = Intent(this@HomeActivity,EnsiklopediaActivity::class.java)
            startActivity(intent)
        }
    }
}