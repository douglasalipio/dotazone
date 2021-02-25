package br.com.dotazone.view.components

import android.content.Context
import android.widget.FrameLayout
import br.com.dotazone.R

class FrameLayoutSkillBoard(context: Context?) : FrameLayout(context!!) {
	fun changeBackgroud() {
		setBackgroundResource(R.drawable.heroes_skill_brightness)
	}

	fun removeBackground() {
		setBackgroundResource(0)
	}
}
