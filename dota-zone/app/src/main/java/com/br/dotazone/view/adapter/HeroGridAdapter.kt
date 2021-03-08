package com.br.dotazone.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.br.dotazone.DotaZoneBrain.TAG
import com.br.dotazone.R
import com.br.dotazone.domain.heroes.prov.Hero


class HeroGridAdapter(private val mContext: Context, private val mHeroes: MutableList<com.br.dotazone.domain.heroes.entity.Hero>) : BaseAdapter() {
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
		val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
		var view = convertView
		Log.i(TAG, "name image [" + mHeroes[position].name + "]")
		val id = mContext.resources.getIdentifier(mHeroes[position].idString, "drawable", mContext.packageName)
		val imageView: ImageView
		if (convertView == null) {

			// get layout from mobile.xml
			view = inflater.inflate(R.layout.hero_item_grindview, parent, false)
			imageView = view.findViewById<View>(R.id.imageView1) as ImageView
		} else {
			// recycle objecte.
			imageView = view?.findViewById<View>(R.id.imageView1) as ImageView
		}
		if (id != 0) {
			imageView.setImageResource(id)
		}
		return view
	}
}
