package com.android.example.batikify.screen.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.android.bottomsheet.ModalSignInBottomSheet
import com.android.bottomsheet.ModalSignUpBottomSheet
import com.android.example.batikify.R
import com.android.example.batikify.factory.AuthViewModelFactory
import com.google.android.material.button.MaterialButton


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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