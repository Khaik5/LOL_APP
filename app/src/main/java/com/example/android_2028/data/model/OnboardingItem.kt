package com.example.android_2028.data.model

import androidx.annotation.DrawableRes

data class OnboardingItem(
    @DrawableRes val imageRes: Int,
    val title: String,
    val description: String
)