package br.com.dotazone.view.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import br.com.dotazone.R
import br.com.dotazone.view.activity.BuildHeroActivity
import br.com.dotazone.view.fragment.ItemFragment.Companion.newInstance


class ItemAdapter : FragmentPagerAdapter {
	private var mActivity: BuildHeroActivity? = null
	private var mContext: Context? = null
	private var content: Array<String> = arrayOf()

	constructor(fm: FragmentManager?, context: Context) : super(fm!!) {
		mContext = context
		content = arrayOf(context.getString(R.string.item_basic), context.getString(R.string.item_improvements),
				context.getString(R.string.item_secret_store))
	}

	constructor(fm: FragmentManager?, activity: BuildHeroActivity) : super(fm!!) {
		mActivity = activity
		content = arrayOf(activity.getString(R.string.item_basic), activity.getString(R.string.item_improvements),
				activity.getString(R.string.item_secret_store))
	}

	override fun getItem(position: Int): Fragment {
		return if (mActivity != null) {
			newInstance(content[position % content.size], mActivity)
		} else newInstance(content[position % content.size])
	}

	override fun getPageTitle(position: Int): CharSequence? {
		return content[position % content.size].toUpperCase()
	}

	override fun getCount(): Int {
		return content.size
	}
}
