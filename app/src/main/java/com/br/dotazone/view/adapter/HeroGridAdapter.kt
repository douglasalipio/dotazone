package com.br.dotazone.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.br.dotazone.R
import kotlinx.android.synthetic.main.hero_item_grindview.view.*


class HeroGridAdapter(private val context: Context, private val mHeroes: MutableList<com.br.dotazone.domain.heroes.entity.Hero>) : BaseAdapter() {
    override fun getCount(): Int {
        return mHeroes.size
    }

    override fun getItem(position: Int): Any {
        return mHeroes[position]
    }

    override fun getItemId(positionId: Int): Long {
        return positionId.toLong()
    }

	override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
		val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
		var view = convertView
		val id = context.resources.getIdentifier(mHeroes[position].idString, "drawable", context.packageName)
		if (convertView == null) {

			// get layout from mobile.xml
			view = inflater.inflate(R.layout.hero_item_grindview, parent, false)

		}
		if (id != 0) {
			view?.heroImageGridView?.setImageResource(id)
		}
		return view
	}
}
