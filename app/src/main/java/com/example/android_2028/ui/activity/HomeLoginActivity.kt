package com.example.android_2028.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.android_2028.databinding.ActivityHomeLoginBinding
import com.example.android_2028.ui.utils.applyBottomInsetMargin


class HomeLoginActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityHomeLoginBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initListener()
        enableEdgeToEdge()
        binding.btnHomeLogin.applyBottomInsetMargin()
    }
    private fun initListener(){
        binding.btnHomeLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}