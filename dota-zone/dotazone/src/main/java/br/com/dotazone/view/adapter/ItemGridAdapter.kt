package br.com.dotazone.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import br.com.dotazone.DotaZoneBrain.TAG
import br.com.dotazone.R
import br.com.dotazone.model.entity.Item


class ItemGridAdapter(private val mContext: Context, private val mItems: List<Item>) : BaseAdapter() {
	override fun getCount(): Int {
		return mItems.size
	}

	override fun getItem(position: Int): Any {
		return mItems[position]
	}

	override fun getItemId(positionId: Int): Long {
		return positionId.toLong()
	}

	override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
		val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
		var view = convertView
		Log.i(TAG, "name image [" + mItems[position].imageName + "]")
		val pos = mItems[position].imageName.lastIndexOf('.')
		val id = mContext.resources.getIdentifier(mItems[position].imageName.substring(0, pos), "drawable",
				mContext.packageName)
		if (convertView == null) {

			// get layout from mobile.xml
			view = inflater.inflate(R.layout.item_item_grindview, parent, false)
		}
		val imageView = view?.findViewById<View>(R.id.imageView1) as ImageView
		imageView.setImageResource(id)
		return view
	}
}
