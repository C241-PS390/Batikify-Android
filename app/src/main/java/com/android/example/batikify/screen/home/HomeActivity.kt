package com.android.example.batikify.screen.home

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.android.example.batikify.R
import com.android.example.batikify.databinding.ActivityHomeBinding
import com.android.example.batikify.screen.classification.ClassificationActivity
import com.android.example.batikify.screen.ensiklopedia.EnsiklopediaActivity
import com.android.example.batikify.screen.profile.ProfileActivity

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                val profilePage =  Intent(this@HomeActivity,ProfileActivity::class.java)
                startActivity(profilePage)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}