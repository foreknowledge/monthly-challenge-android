package com.gdghackathon.monthlychallenges.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.gdghackathon.monthlychallenges.R
import com.gdghackathon.monthlychallenges.databinding.FragmentSetChallengeTitleBinding

class SetChallengeTitleFragment : Fragment() {
    private var shortAnimationDuration: Int = 0

    private lateinit var binding: FragmentSetChallengeTitleBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_set_challenge_title, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)

        binding.edittextChallengeTitle.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0 != null) {
                    binding.buttonConfirmChallengeTitle.isEnabled = (p0.length in 1..20)

                    if (p0.length > 20) {
                        fadeIn(binding.textviewChallengeTitleDescription)
                    } else {
                        binding.textviewChallengeTitleDescription.visibility = View.GONE
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun afterTextChanged(p0: Editable?) { }
        })
    }

    private fun fadeIn(view: TextView) {
        view.apply {
            alpha = 0f
            visibility = View.VISIBLE

            animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration.toLong())
                .setListener(null)
        }
    }
}