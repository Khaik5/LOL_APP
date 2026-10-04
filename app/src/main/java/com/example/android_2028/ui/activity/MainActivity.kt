package com.example.android_2028.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_2028.databinding.ActivityMainBinding
import com.example.android_2028.ui.adapter.MainPagerAdapter
import com.example.android_2028.ui.utils.applyBottomInsetMargin

class MainActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityMainBinding.inflate(layoutInflater) }
    private val adapter = MainPagerAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding.btnNext.applyBottomInsetMargin()
        setContentView(binding.root)
        initView()
        initListener()

    }
    private fun initView(){
        binding.vp2.adapter = adapter
        binding.vp2.isUserInputEnabled = false
    }
    private fun initListener(){
        binding.btnNext.setOnClickListener {
            val currentPage = binding.vp2.currentItem
            if(currentPage == adapter.itemCount -2){
                binding.btnNext.visibility = View.GONE
            } else {
                View.VISIBLE
            }
            if(currentPage == adapter.itemCount -1){
                val intent = Intent(this, HomeLoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                binding.vp2.currentItem += 1
            }
        }
    }
}