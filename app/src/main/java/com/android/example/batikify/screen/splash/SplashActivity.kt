package com.android.example.batikify.screen.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.example.batikify.databinding.ActivitySplashBinding
import com.android.example.batikify.factory.AuthViewModelFactory
import com.android.example.batikify.screen.home.HomeActivity
import com.android.example.batikify.screen.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val splashScreenTimeDelay = 1000L
    private val splashViewModel by viewModels<SplashViewModel> {
        AuthViewModelFactory.getAuthInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        splashViewModel.getSession().observe(this) { userModel ->
            lifecycleScope.launch {
                delay(splashScreenTimeDelay)
                if (userModel != null) {
                    navigateToHome()
                } else {
                    navigateToMain()
                }
            }
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this@SplashActivity, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToMain() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
