package com.android.example.batikify.screen.profile

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.android.example.batikify.databinding.ActivityProfileBinding
import com.android.example.batikify.factory.ViewModelFactory
import com.android.example.batikify.screen.home.HomeActivity
import com.android.example.batikify.screen.main.MainActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    private val profileViewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        profileViewModel.getProfile()
        setupAction()
        startLogoAnimation()
    }

    private fun setupAction() {
        profileViewModel.fullName.observe(this) { fullName ->
            binding.username.text = fullName
        }

        profileViewModel.email.observe(this) { email ->
            binding.email.text = email
        }

        profileViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        binding.btnLogout.setOnClickListener {
            profileViewModel.logout()
            val intent = Intent(this@ProfileActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        binding.btnHomePage.setOnClickListener {
            val intent = Intent(this@ProfileActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun startLogoAnimation() {
        val logo = binding.ivLogo
        val radius = 100f
        val duration = 3000L

        val animator = ValueAnimator.ofFloat(0f, 360f).apply {
            this.duration = duration
            repeatCount = ValueAnimator.INFINITE
            interpolator = LinearInterpolator()
            addUpdateListener { animation ->
                val angle = (animation.animatedValue as Float) * (Math.PI / -180f)
                val translationX = (radius * Math.cos(angle)).toFloat()
                val translationY = (radius * Math.sin(angle)).toFloat()
                logo.translationX = translationX
                logo.translationY = translationY
            }
        }

        animator.start()
    }

}
