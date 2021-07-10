package com.gdghackathon.monthlychallenges.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.gdghackathon.monthlychallenges.R

class SetChallengeTitleFragment : Fragment() {
    private var shortAnimationDuration: Int = 0

    lateinit var challengeTitle: EditText
    lateinit var challengeTitleDescription: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_set_challenge_title, container, false)

        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)

        challengeTitle = view.findViewById(R.id.edittext_challenge_title)
        challengeTitleDescription = view.findViewById(R.id.textview_challenge_title_description)
        val confirmChallengeTitleButton =
            view.findViewById<AppCompatButton>(R.id.button_confirm_challenge_title)

        challengeTitle.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0 != null) {
                    confirmChallengeTitleButton.isEnabled = (p0.length in 1..20)

                    if (p0.length > 20) {
                        fadeIn(challengeTitleDescription)
                    } else {
                        challengeTitleDescription.visibility = View.GONE
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun afterTextChanged(p0: Editable?) { }
        })

        return view
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