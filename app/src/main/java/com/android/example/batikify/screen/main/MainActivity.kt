package com.android.example.batikify.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.bottomsheet.ModalSignInBottomSheet
import com.android.example.batikify.R
import com.android.example.batikify.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnSignInShowBottomSheet = findViewById<MaterialButton>(R.id.btnSignIn)
        btnSignInShowBottomSheet.setOnClickListener {
            val modalSignInBottomSheet = ModalSignInBottomSheet()
            modalSignInBottomSheet.show(supportFragmentManager, ModalSignInBottomSheet.TAG)
        }

        val btnSignUpShowBottomSheet = findViewById<MaterialButton>(R.id.btnSignUp)
        btnSignUpShowBottomSheet.setOnClickListener {
            val modalSignInBottomSheet = ModalSignUpBottomSheet()
            modalSignInBottomSheet.show(supportFragmentManager, ModalSignUpBottomSheet.TAG)
        }
    }
}