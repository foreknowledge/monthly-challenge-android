package com.gdghackathon.monthlychallenges.ui.adapter

import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.RecyclerView
import com.gdghackathon.monthlychallenges.databinding.ItemMissionBinding
import com.gdghackathon.monthlychallenges.listener.OnEditTextChangedListener
import com.gdghackathon.monthlychallenges.listener.OnItemClickListener
import com.gdghackathon.monthlychallenges.model.data.Mission

class MissionViewHolder(
        private val binding: ItemMissionBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(mission: Mission,
             editable: Boolean,
             onEditTextChangedListener: OnEditTextChangedListener,
             onItemClickListener: OnItemClickListener<Mission>?) {
        binding.run {
            this.editable = editable
            this.mission = mission
            listener = onItemClickListener

            itemText.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    onEditTextChangedListener.onEditTextChanged(adapterPosition, s.toString())
                }

                override fun afterTextChanged(s: Editable) {}
            })

            executePendingBindings()
        }
    }
}