package com.gdghackathon.monthlychallenges.ui

import androidx.recyclerview.widget.RecyclerView
import com.gdghackathon.monthlychallenges.databinding.ItemMissionBinding
import com.gdghackathon.monthlychallenges.listener.OnItemClickListener
import com.gdghackathon.monthlychallenges.model.Mission

class MissionViewHolder(
        private val binding: ItemMissionBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(mission: Mission, onItemClickListener: OnItemClickListener<Mission>?) {
        binding.run {
            this.mission = mission
            listener = onItemClickListener

            executePendingBindings()
        }
    }
}