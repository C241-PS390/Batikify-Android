package com.android.example.batikify.screen.ensiklopedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.example.batikify.databinding.ActivityEnsiklopediaBinding

class EnsiklopediaActivity : AppCompatActivity(){

    private lateinit var binding : ActivityEnsiklopediaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnsiklopediaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}