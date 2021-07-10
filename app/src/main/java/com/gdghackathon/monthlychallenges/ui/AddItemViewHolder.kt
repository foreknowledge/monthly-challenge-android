package com.gdghackathon.monthlychallenges.ui

import androidx.recyclerview.widget.RecyclerView
import com.gdghackathon.monthlychallenges.databinding.ItemAddBinding

class AddItemViewHolder(
    private val binding: ItemAddBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(onItemClick: () -> Unit) {
        binding.itemText.setOnClickListener { onItemClick() }
    }
}