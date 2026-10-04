package com.example.android_2028.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_2028.data.model.BadgeModel
import com.example.android_2028.databinding.ItemBadgeBinding

class BadgeAdapter(
    private var listBadge: List<BadgeModel>
) : RecyclerView.Adapter<BadgeAdapter.BadgeViewHolder>() {

    fun updateData(newList: List<BadgeModel>) {
        listBadge = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BadgeViewHolder {

        val binding = ItemBadgeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return BadgeViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BadgeViewHolder,
        position: Int
    ) {
        holder.updateUi(listBadge[position])
    }

    override fun getItemCount(): Int = listBadge.size

    inner class BadgeViewHolder(
        private val binding: ItemBadgeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun updateUi(data: BadgeModel) {
            binding.tvNameBadge.text = data.name
            binding.ivBadge.setImageResource(data.imgRes)
        }
    }
}