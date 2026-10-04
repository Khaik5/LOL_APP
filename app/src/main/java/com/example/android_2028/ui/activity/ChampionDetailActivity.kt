package com.example.android_2028.ui.activity

import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.android_2028.data.model.SkillUiModel
import com.example.android_2028.databinding.ActivityDetailChapionBinding
import com.example.android_2028.ui.utils.applyBottomInsetMargin
import com.example.android_2028.ui.utils.applyTopInsetMargin
import com.example.android_2028.ui.viewmodel.ChampionDetailViewModel
import kotlinx.coroutines.launch

class ChampionDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailChapionBinding.inflate(layoutInflater)
    }

    private val viewModel: ChampionDetailViewModel by viewModels()

    private lateinit var skillViews: List<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.btnBack.applyTopInsetMargin()
        binding.btnSkins.applyBottomInsetMargin()
        skillViews = listOf(
            binding.ivSkillPassive,
            binding.ivSkillQ,
            binding.ivSkillW,
            binding.ivSkillE,
            binding.ivSkillR
        )

        val championId = intent.getStringExtra("champion_id")

        if (championId == null) {
            finish()
            return
        }

        initListener()
        initCollect()

        viewModel.loadChampionDetail(championId)
    }

    private fun initListener() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun initCollect() {
        lifecycleScope.launch {
            viewModel.detail.collect { detail ->
                detail ?: return@collect

                binding.tvTitleDetail1.text = detail.name
                binding.tvDescriptionDetail1.text = detail.title
                binding.tvTitleDetail2.text = detail.lore
                binding.tvDescription5.text = detail.role
                binding.tvTitleDetail8.text = detail.difficulty

                Glide.with(this@ChampionDetailActivity).load(detail.splashUrl).centerCrop().into(binding.ivImage)

                detail.skills.forEachIndexed { index, skill ->
                    Glide.with(this@ChampionDetailActivity).load(skill.iconUrl).into(skillViews[index])

                    skillViews[index].setOnClickListener {
                        showSkill(skill)
                    }
                }

                if (detail.skills.size > 1) {
                    showSkill(detail.skills[1])
                }
            }
        }
    }

    private fun showSkill(skill: SkillUiModel) {
        binding.tvSkillKey.text = skill.key
        binding.tvTitleDetail3.text = skill.name.uppercase()
        binding.tvTitleDetail4.text =
            Html.fromHtml(skill.description, Html.FROM_HTML_MODE_LEGACY)
    }
}