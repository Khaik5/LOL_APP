package com.example.android_2028.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android_2028.R
import com.example.android_2028.databinding.FragmentSearchBinding
import com.example.android_2028.ui.activity.ChampionDetailActivity
import com.example.android_2028.ui.adapter.ChampionAdapter
import com.example.android_2028.ui.adapter.ItemAdapter
import com.example.android_2028.ui.viewmodel.SearchViewModel
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private val binding by lazy {
        FragmentSearchBinding.inflate(layoutInflater)
    }

    private val viewModel: SearchViewModel by viewModels()

    private lateinit var championAdapter: ChampionAdapter
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        initListener()
        initCollect()
        viewModel.loadData()
    }

    private fun initView() {
        championAdapter = ChampionAdapter(emptyList()) { champion ->
            val intent = Intent(requireContext(), ChampionDetailActivity::class.java)
            intent.putExtra("champion_id", champion.id)
            startActivity(intent)
        }

        itemAdapter = ItemAdapter(emptyList())

        binding.rvSearch.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvSearch.adapter = championAdapter
    }

    private fun initListener() {
        binding.btnBack.setOnClickListener {
            requireActivity()
                .findViewById<androidx.viewpager2.widget.ViewPager2>(R.id.vp2)
                .currentItem = 0
        }

        binding.btnCancel.setOnClickListener {
            binding.edtSearch.setText("")
        }

        binding.tvChampions.setOnClickListener {
            viewModel.changeTab("Champions")
        }

        binding.tvItems.setOnClickListener {
            viewModel.changeTab("Items")
        }

        binding.tvRuneterra.setOnClickListener {
            viewModel.changeTab("Runeterra")
        }

        binding.tvSkins.setOnClickListener {
            viewModel.changeTab("Skins")
        }

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.search(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun initCollect() {
        lifecycleScope.launch {
            viewModel.selectedTab.collect { tab ->
                updateTab(tab)

                binding.rvSearch.adapter = when (tab) {
                    "Items" -> itemAdapter
                    else -> championAdapter
                }
            }
        }

        lifecycleScope.launch {
            viewModel.champions.collect {
                championAdapter.updateData(it)
                updateEmptyState(it.isEmpty() && binding.edtSearch.text.toString().isBlank())
            }
        }

        lifecycleScope.launch {
            viewModel.items.collect {
                itemAdapter.updateData(it)
            }
        }
    }

    private fun updateTab(tab: String) {
        binding.tvChampions.isSelected = tab == "Champions"
        binding.tvItems.isSelected = tab == "Items"
        binding.tvRuneterra.isSelected = tab == "Runeterra"
        binding.tvSkins.isSelected = tab == "Skins"
    }

    private fun updateEmptyState(show: Boolean) {
        binding.layoutEmpty.visibility = if (show) View.VISIBLE else View.GONE
        binding.rvSearch.visibility = if (show) View.GONE else View.VISIBLE
    }
}