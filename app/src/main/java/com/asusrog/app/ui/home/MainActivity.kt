package com.asusrog.app.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.asusrog.app.R
import com.asusrog.app.databinding.ActivityMainBinding
import com.asusrog.app.ui.auth.ApploginAuth
import com.asusrog.app.ui.auth.AuthActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.buttonLogout.setOnClickListener {
            ApploginAuth.logout(this    ) {
                startActivity(Intent(this, AuthActivity::class.java))
                finish()
            }
        }
    }
}