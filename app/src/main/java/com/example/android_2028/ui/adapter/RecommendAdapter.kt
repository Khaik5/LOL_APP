package com.example.android_2028.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_2028.data.model.ChampionUiModel
import com.example.android_2028.databinding.ItemChampionRecommentBinding

class RecommendAdapter(
    private var listChampion: List<ChampionUiModel>,
    private val onClick: (ChampionUiModel) -> Unit,
    private val onNextClick: (Int) -> Unit
) : RecyclerView.Adapter<RecommendAdapter.RecommendedViewHolder>() {

    fun updateData(newList: List<ChampionUiModel>) {
        listChampion = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedViewHolder {
        val binding = ItemChampionRecommentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecommendedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendedViewHolder, position: Int) {
        holder.updateUi(listChampion[position], position)
    }

    override fun getItemCount(): Int = listChampion.size

    inner class RecommendedViewHolder(
        private val binding: ItemChampionRecommentBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun updateUi(champion: ChampionUiModel, position: Int) {
            binding.tvNameChampion.text = champion.name
            binding.tvTitleChampion.text = champion.title

            Glide.with(binding.root.context).load(champion.splashUrl).centerCrop().into(binding.ivBg)
            Glide.with(binding.root.context).load(champion.splashUrl).centerCrop().into(binding.ivChampion)
            binding.root.setOnClickListener {
                onClick(champion)
            }
            binding.btnNextRecommend.setOnClickListener {
                onNextClick(position)
            }
        }
    }
}