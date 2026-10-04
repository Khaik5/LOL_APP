package com.example.android_2028.ui.utils

import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

fun View.applyTopInsetMargin(extraTop: Int = 0) {
    val originalTop = (layoutParams as ViewGroup.MarginLayoutParams).topMargin

    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        val top = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.topMargin = originalTop + top + extraTop
        view.layoutParams = layoutParams
        insets
    }
}

fun View.applyBottomInsetMargin(extraBottom: Int = 0) {
    val originalBottom = (layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        val bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.bottomMargin = originalBottom + bottom + extraBottom
        view.layoutParams = layoutParams
        insets
    }
}