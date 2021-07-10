package com.gdghackathon.monthlychallenges.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdghackathon.monthlychallenges.databinding.ItemAddBinding
import com.gdghackathon.monthlychallenges.databinding.ItemMissionBinding
import com.gdghackathon.monthlychallenges.listener.OnItemClickListener
import com.gdghackathon.monthlychallenges.listener.OnItemSingleClickListener
import com.gdghackathon.monthlychallenges.model.Mission

class MissionListRecyclerAdapter(
        private val missionList: MutableList<Mission>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onMissionItemClickListener: OnItemClickListener<Mission>? = null

    fun setOnItemClickListener(listener: (item: Mission) -> Unit) {
        this.onMissionItemClickListener = object : OnItemSingleClickListener<Mission>() {
            override fun onSingleClick(item: Mission) {
                listener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            if (viewType == VIEW_TYPE_MISSION)
                MissionViewHolder(ItemMissionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else
                AddItemViewHolder(ItemAddBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
            when (holder) {
                is MissionViewHolder -> holder.bind(missionList[position], onMissionItemClickListener)
                is AddItemViewHolder -> holder.bind { onAddItemClick() }
                else -> {
                }
            }

    override fun getItemCount() = (missionList.size + 1).coerceAtMost(NUM_OF_MISSIONS)

    override fun getItemViewType(position: Int) = if (position < missionList.size) VIEW_TYPE_MISSION else VIEW_TYPE_ADD

    private fun onAddItemClick() {
        missionList.add(Mission(-1, "", false))

        val positionToAdd = missionList.size - 1
        if (missionList.size < NUM_OF_MISSIONS)
            notifyItemInserted(positionToAdd)
        else
            notifyItemChanged(positionToAdd)
    }

    companion object {
        private const val NUM_OF_MISSIONS = 30

        private const val VIEW_TYPE_MISSION = 1
        private const val VIEW_TYPE_ADD = 2
    }
}