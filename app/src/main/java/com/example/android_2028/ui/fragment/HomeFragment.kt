package com.example.android_2028.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_2028.databinding.FragmentHomeBinding
import com.example.android_2028.ui.activity.ChampionDetailActivity
import com.example.android_2028.ui.adapter.BadgeAdapter
import com.example.android_2028.ui.adapter.ChampionAdapter
import com.example.android_2028.ui.adapter.RecommendAdapter
import com.example.android_2028.ui.utils.applyTopInsetMargin
import com.example.android_2028.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var championAdapter: ChampionAdapter
    private lateinit var recommendAdapter: RecommendAdapter
    private lateinit var badgeAdapter: BadgeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.layoutHeader.applyTopInsetMargin(8)
        initView()
        initCollect()
        viewModel.loadHomeData()
    }

    private fun initView() {
        championAdapter = ChampionAdapter(emptyList()) { champion ->
            openDetail(champion.id)
        }

        recommendAdapter = RecommendAdapter(
            emptyList(),
            onClick = { champion ->
                openDetail(champion.id)
            },
            onNextClick = { position ->
                binding.rvRecomment.smoothScrollToPosition(position + 1)
            }
        )

        badgeAdapter = BadgeAdapter(emptyList())

        binding.rvChampion.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvChampion.adapter = championAdapter

        binding.rvRecomment.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecomment.adapter = recommendAdapter

        binding.rvBadge.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvBadge.adapter = badgeAdapter
    }

    private fun initCollect() {
        lifecycleScope.launch {
            viewModel.champions.collect {
                championAdapter.updateData(it)
            }
        }

        lifecycleScope.launch {
            viewModel.recommendedChampions.collect {
                recommendAdapter.updateData(it)
            }
        }

        lifecycleScope.launch {
            viewModel.badge.collect {
                badgeAdapter.updateData(it)
            }
        }

        lifecycleScope.launch {
            viewModel.toast.collect {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openDetail(championId: String) {
        val intent = Intent(requireContext(), ChampionDetailActivity::class.java)
        intent.putExtra("champion_id", championId)
        startActivity(intent)
    }
}