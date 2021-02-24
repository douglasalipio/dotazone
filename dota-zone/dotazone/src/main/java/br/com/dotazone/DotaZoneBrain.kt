package br.com.dotazone

import br.com.dotazone.model.entity.Hero
import br.com.dotazone.model.entity.Item
import com.prof.rssparser.Article
import java.util.*

object DotaZoneBrain {
	val TAG = DotaZoneBrain::class.java.name
	const val RATING_DOTA_ZONE = "rating_dota_zone"
	var rssItems: List<Article>? = null
	var items: List<Item> = ArrayList()
	var heroes: List<Hero> = ArrayList()
	var hero: Hero? = null

	//TODO Issue https://github.com/douglasalipio/dotazone/issues/2
	var isPremium = true
}

