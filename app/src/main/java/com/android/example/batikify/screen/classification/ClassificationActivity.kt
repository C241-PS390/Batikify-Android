package com.android.example.batikify.screen.classification

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.android.example.batikify.databinding.ActivityClassificationBinding

class ClassificationActivity : AppCompatActivity(){

    private lateinit var binding : ActivityClassificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClassificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}