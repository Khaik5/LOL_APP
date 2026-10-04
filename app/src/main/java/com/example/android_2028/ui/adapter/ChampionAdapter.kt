package com.example.android_2028.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_2028.data.model.ChampionUiModel
import com.example.android_2028.databinding.ItemChampionBinding

class ChampionAdapter(
    private var listChampion: List<ChampionUiModel>,
    private val onClick: (ChampionUiModel) -> Unit
) : RecyclerView.Adapter<ChampionAdapter.ChampionViewHolder>() {

    fun updateData(newList: List<ChampionUiModel>) {
        listChampion = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionViewHolder {
        val binding = ItemChampionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChampionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChampionViewHolder, position: Int) {
        holder.updateUi(listChampion[position])
    }

    override fun getItemCount(): Int = listChampion.size

    inner class ChampionViewHolder(
        private val binding: ItemChampionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun updateUi(champion: ChampionUiModel) {
            binding.tvNameChampion.text = champion.name
            binding.tvTitleChampion.text = champion.title

            Glide.with(binding.root.context)
                .load(champion.iconUrl)
                .into(binding.ivChampion)

            binding.root.setOnClickListener {
                onClick(champion)
            }
        }
    }
}