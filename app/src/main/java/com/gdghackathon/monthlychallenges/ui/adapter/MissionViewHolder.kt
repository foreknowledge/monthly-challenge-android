package com.gdghackathon.monthlychallenges.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.gdghackathon.monthlychallenges.databinding.ItemMissionBinding
import com.gdghackathon.monthlychallenges.listener.OnItemClickListener
import com.gdghackathon.monthlychallenges.model.Mission

class MissionViewHolder(
        private val binding: ItemMissionBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(mission: Mission, editable: Boolean, onItemClickListener: OnItemClickListener<Mission>?) {
        binding.run {
            this.editable = editable
            this.mission = mission
            listener = onItemClickListener

            executePendingBindings()
        }
    }
}