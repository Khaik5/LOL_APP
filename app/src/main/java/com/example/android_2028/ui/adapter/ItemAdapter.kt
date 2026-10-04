package com.example.android_2028.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_2028.data.model.ItemUiModel
import com.example.android_2028.databinding.ItemItemBinding

class ItemAdapter(
    private var listItem: List<ItemUiModel>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    fun updateData(newList: List<ItemUiModel>) {
        listItem = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.updateUi(listItem[position])
    }

    override fun getItemCount(): Int = listItem.size

    inner class ItemViewHolder(
        private val binding: ItemItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun updateUi(item: ItemUiModel) {
            binding.tvNameItem.text = item.name
            binding.tvDescriptionItem.text = item.description
            Glide.with(binding.root.context)
                .load(item.iconUrl)
                .into(binding.ivItem)
        }
    }
}