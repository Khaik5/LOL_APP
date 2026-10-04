package com.example.android_2028.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.android_2028.data.model.OnboardingItem
import com.example.android_2028.ui.fragment.HomeFragment
import com.example.android_2028.ui.fragment.SearchFragment

class MainHomeAdapter(
    fragmentActivity: FragmentActivity,

) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int{
        return 2
    }
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> SearchFragment()
            else -> HomeFragment()
        } as Fragment
    }
}