package com.gdghackathon.monthlychallenges.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdghackathon.monthlychallenges.NUM_OF_MISSIONS
import com.gdghackathon.monthlychallenges.databinding.ItemAddBinding
import com.gdghackathon.monthlychallenges.databinding.ItemMissionBinding
import com.gdghackathon.monthlychallenges.listener.OnEditTextChangedListener
import com.gdghackathon.monthlychallenges.listener.OnItemClickListener
import com.gdghackathon.monthlychallenges.listener.OnItemSingleClickListener
import com.gdghackathon.monthlychallenges.model.data.Mission

class MissionListRecyclerAdapter(
        val missionList: MutableList<Mission>,
        private val editable: Boolean = true
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val onEditTextChangedListener = object : OnEditTextChangedListener {
        override fun onEditTextChanged(position: Int, text: String) {
            missionList[position].name = text
        }
    }

    private var onMissionItemClickListener: OnItemClickListener<Mission>? = null
    var onAddItemClick: (missionList: List<Mission>) -> Unit = {}

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
                is MissionViewHolder -> holder.bind(position, missionList[position], editable,
                        onEditTextChangedListener, onMissionItemClickListener)
                is AddItemViewHolder -> holder.bind {
                    addNewMission()
                    onAddItemClick(missionList)
                }
                else -> {
                }
            }

    override fun getItemCount() =
            if (editable) {
                // 편집 가능한 경우 [+ 추가] 아이템 표시
                (missionList.size + 1).coerceAtMost(NUM_OF_MISSIONS)
            } else {
                // 편집 가능한 경우 [+ 추가] 아이템 표시 X
                missionList.size
            }

    override fun getItemViewType(position: Int) = if (position < missionList.size) VIEW_TYPE_MISSION else VIEW_TYPE_ADD

    private fun addNewMission() {
        missionList.add(Mission())

        val positionToAdd = missionList.size - 1
        if (missionList.size < NUM_OF_MISSIONS)
            notifyItemInserted(positionToAdd)
        else
            notifyItemChanged(positionToAdd)
    }

    companion object {
        private const val VIEW_TYPE_MISSION = 1
        private const val VIEW_TYPE_ADD = 2
    }
}