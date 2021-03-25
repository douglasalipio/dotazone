package com.br.dotazone

import com.br.dotazone.domain.heroes.prov.Hero
import com.br.dotazone.domain.heroes.prov.Item
import com.prof.rssparser.Article

object DotaZoneBrain {
	val TAG = DotaZoneBrain::class.java.name
	const val RATING_DOTA_ZONE = "rating_dota_zone"
	var rssItems: List<Article>? = null
	var items: MutableList<Item> = mutableListOf()
	var heroes: MutableList<com.br.dotazone.domain.heroes.entity.Hero> = mutableListOf()
	var hero: Hero? = null

	//TODO Issue https://github.com/douglasalipio/dotazone/issues/2
	var isPremium = true
}

