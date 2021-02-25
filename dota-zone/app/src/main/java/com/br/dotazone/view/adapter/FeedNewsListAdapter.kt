package com.br.dotazone.view.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.br.dotazone.DotaZoneBrain.TAG
import com.br.dotazone.DotaZoneBrain.rssItems
import com.br.dotazone.R
import com.br.dotazone.model.listeners.Controllable


class FeedNewsListAdapter(private val mContext: Context) : BaseAdapter(), Controllable {
	private val mInflate: LayoutInflater
	override fun getCount(): Int {
		Log.d(TAG, "getCount  " + rssItems!!.size + "")
		return rssItems!!.size
	}

	override fun getItem(position: Int): Any {
		Log.d(TAG, "getItem  $position")
		return rssItems!![position]
	}

	override fun getItemId(positionId: Int): Long {
		Log.d(TAG, "getItemId  $positionId")
		return positionId.toLong()
	}

	override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
		var view = convertView
		Log.d("teste", "position in view  $position")
		if (view == null) {
			view = mInflate.inflate(R.layout.feed_news_item, parent, false)
		}
		val font = Typeface.createFromAsset(mContext.assets, "Roboto-Light.ttf")
		val titleNews = view?.findViewById<View>(R.id.textItemTitle) as TextView
		titleNews.text = rssItems!![position].title
		val hourNews = view.findViewById<View>(R.id.textItemHour) as TextView
		hourNews.text = rssItems!![position].pubDate
		val descriptionNews = view.findViewById<View>(R.id.textItemDescription) as TextView
		descriptionNews.text = Html.fromHtml(rssItems!![position].description)
		descriptionNews.setOnClickListener {
			val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(rssItems!![position].link))
			mContext.startActivity(browserIntent)
		}
		titleNews.setTypeface(font)
		hourNews.setTypeface(font)
		descriptionNews.setTypeface(font)
		return view
	}

	override fun menu(isOpen: Boolean) {
		// TODO Auto-generated method stub
	}

	init {
		mInflate = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
	}
}
