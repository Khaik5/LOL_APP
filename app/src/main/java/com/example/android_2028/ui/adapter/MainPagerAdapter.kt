package com.example.android_2028.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.android_2028.R
import com.example.android_2028.data.model.OnboardingItem
import com.example.android_2028.ui.fragment.OnboardingFragment

class MainPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    private val onboardingList = listOf(
        OnboardingItem(
            imageRes = R.drawable.img_onboarding_1,
            title = "Chọn những vị tướng tốt nhất",
            description = "Những vị tướng mạnh nhất, bảng ngọc/trang bị, kèo đối đầu và mọi thứ bạn cần để cải thiện kỹ năng trong trò chơi."
        ),
        OnboardingItem(
            imageRes = R.drawable.img_onboarding_2,
            title = "Leo hạng nhanh chóng",
            description = "Tại đây, bạn không chỉ học về trò chơi mà còn hiểu rõ mọi thứ về các vị tướng."
        ),
        OnboardingItem(
            imageRes = R.drawable.img_onboarding_3,
            title = "Hãy tận hưởng trò chơi",
            description = "Chào mừng bạn đến với ứng dụng sẽ giúp bạn nâng trình và đưa kỹ năng chơi Liên Minh Huyền Thoại lên một tầm cao mới."
        )
    )

    override fun getItemCount(): Int = onboardingList.size

    override fun createFragment(position: Int): Fragment {
        val item = onboardingList[position]

        return OnboardingFragment.newInstance(
            imageRes = item.imageRes,
            title = item.title,
            description = item.description,
            page = position
        )
    }
}