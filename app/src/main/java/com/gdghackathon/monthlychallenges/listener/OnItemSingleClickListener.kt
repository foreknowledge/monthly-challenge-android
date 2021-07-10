package com.gdghackathon.monthlychallenges.listener

import android.os.SystemClock

abstract class OnItemSingleClickListener<T>(
	private val clickInterval: Long = CLICK_INTERVAL
): OnItemClickListener<T> {
	private var mLastClickTime = 0L

	override fun onClick(item: T) {
		// 중복 클릭 방지
		if (SystemClock.elapsedRealtime() - mLastClickTime < clickInterval) return
		mLastClickTime = SystemClock.elapsedRealtime()

		onSingleClick(item)
	}

	abstract fun onSingleClick(item: T)

    companion object {
        private const val CLICK_INTERVAL = 500L
    }
}