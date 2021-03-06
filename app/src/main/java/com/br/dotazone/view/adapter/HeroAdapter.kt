package com.br.dotazone.view.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.br.dotazone.R
import com.br.dotazone.heroes.HeroFragment.Companion.newInstance


class HeroAdapter(fm: FragmentManager?, private val mContext: Context) : FragmentPagerAdapter(fm!!) {
	private var content: Array<String> = arrayOf()

	override fun getItem(position: Int): Fragment {
		return newInstance(content[position % content.size])
	}

	override fun getPageTitle(position: Int): CharSequence? {
		return content[position % content.size].toUpperCase()
	}

	override fun getCount(): Int {
		return content.size
	}

	companion object {
	}

	init {
		content = arrayOf(mContext.getString(R.string.hero_agility), mContext.getString(R.string.hero_strength),
				mContext.getString(R.string.hero_intelligence))
	}
}
