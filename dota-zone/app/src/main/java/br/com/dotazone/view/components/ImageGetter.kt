package br.com.dotazone.view.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Html
import br.com.dotazone.R

class ImageGetter(private val context: Context) : Html.ImageGetter {

	override fun getDrawable(source: String): Drawable {
		var d: Drawable = context.resources.getDrawable(R.drawable.items_icon_mana)
		if (source.indexOf("mana") != -1) {
			d = context.resources.getDrawable(R.drawable.items_icon_mana)
			d.setBounds(0, 0, d.intrinsicWidth, d.intrinsicHeight)
		} else if (source.indexOf("cooldown") != -1) {
			d = context.resources.getDrawable(R.drawable.items_icon_duration)
			d.setBounds(0, 0, d.intrinsicWidth, d.intrinsicHeight)
		}
		return d
	}
}
