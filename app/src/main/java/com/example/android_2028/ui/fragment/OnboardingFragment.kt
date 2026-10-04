package com.example.android_2028.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.android_2028.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {

    private val binding by lazy {
        FragmentOnboardingBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        arguments?.let {
            binding.apply {
                ivOnboarding.setImageResource(it.getInt(ARG_IMAGE))
                tvTitle.text = it.getString(ARG_TITLE)
                tvDescription.text = it.getString(ARG_DESCRIPTION)
                val page = it.getInt(ARG_PAGE)
                btnGameHandle.isVisible = page == 0
                btnPlay.isVisible = page == 0
            }
        }
    }

    companion object {

        private const val ARG_IMAGE = "ARG_IMAGE"
        private const val ARG_TITLE = "ARG_TITLE"
        private const val ARG_DESCRIPTION = "ARG_DESCRIPTION"
        private const val ARG_PAGE = "ARG_PAGE"

        fun newInstance(
            @DrawableRes imageRes: Int,
            title: String,
            description: String,
            page: Int
        ) = OnboardingFragment().apply {

            arguments = Bundle().apply {
                putInt(ARG_IMAGE, imageRes)
                putString(ARG_TITLE, title)
                putString(ARG_DESCRIPTION, description)
                putInt(ARG_PAGE, page)
            }

        }
    }
}