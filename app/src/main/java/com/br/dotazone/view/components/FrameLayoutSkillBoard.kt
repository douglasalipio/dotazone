package com.br.dotazone.view.components

import android.content.Context
import android.widget.FrameLayout
import com.br.dotazone.R

class FrameLayoutSkillBoard(context: Context?) : FrameLayout(context!!) {
	fun changeBackgroud() {
		setBackgroundResource(R.drawable.heroes_skill_brightness)
	}

	fun removeBackground() {
		setBackgroundResource(0)
	}
}
