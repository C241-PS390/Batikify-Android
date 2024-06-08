package com.android.example.batikify.screen.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.android.example.batikify.databinding.ActivityDetailBatikBinding
import com.android.example.batikify.databinding.ActivityProfileBinding
import com.android.example.batikify.factory.ViewModelFactory
import com.android.example.batikify.screen.detail.DetailViewModel
import com.android.example.batikify.screen.home.HomeActivity
import com.android.example.batikify.screen.main.MainActivity

class ProfileActivity : AppCompatActivity(){

    private lateinit var binding: ActivityProfileBinding

    private val profileViewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profileViewModel.getProfile()

        setupAction()
    }

    private fun setupAction(){
        profileViewModel.fullName.observe(this){ fullName ->
            binding.username.hint = fullName
        }

        profileViewModel.email.observe(this){ email ->
            binding.email.hint = email
        }

        profileViewModel.isLoading.observe(this){
            showLoading(it)
        }

        binding.btnLogout.setOnClickListener{
            profileViewModel.logout()
            val intent = Intent(this@ProfileActivity, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnHomePage.setOnClickListener{
            val intent = Intent(this@ProfileActivity,HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}