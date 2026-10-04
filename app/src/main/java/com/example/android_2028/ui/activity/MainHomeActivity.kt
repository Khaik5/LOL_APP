package com.example.android_2028.ui.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.android_2028.databinding.ActivityMainHomeBinding
import com.example.android_2028.ui.adapter.MainHomeAdapter
import com.example.android_2028.ui.utils.applyTopInsetMargin

class MainHomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainHomeBinding.inflate(layoutInflater)
    }

    private val adapter = MainHomeAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        enableEdgeToEdge()
        binding.btnSearch.applyTopInsetMargin()
        initView()
        initListener()
    }

    private fun initView() {
        binding.vp2.adapter = adapter
        binding.vp2.isUserInputEnabled = false

        binding.vp2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.btnSearch.visibility = if (position == 0) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }
        })
    }

    private fun initListener() {
        binding.btnSearch.setOnClickListener {
            binding.vp2.currentItem = 1
        }
    }
}