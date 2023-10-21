package com.stalker.recipesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.stalker.recipesapp.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBackButton.setOnClickListener{
            onClickBackButtton()
        }
    }

    private fun onClickBackButtton() {
        intentToMainActivity()
    }

    private fun intentToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}