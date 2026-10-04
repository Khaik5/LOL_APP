package com.example.android_2028.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.android_2028.databinding.ActivitySplashBinding
import com.example.android_2028.ui.viewmodel.SplashViewModel
import kotlinx.coroutines.launch

class SplashActivity: AppCompatActivity() {
    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }
    private val viewMode: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initCollect()
        viewMode.loadSplash()
    }
    private fun initCollect(){
        lifecycleScope.launch {
            viewMode.event.collect { load->
                if(load){
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}