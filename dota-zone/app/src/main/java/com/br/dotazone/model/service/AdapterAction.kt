package com.br.dotazone.model.service

import android.view.View
import com.br.dotazone.domain.heroes.prov.Hero;
import com.br.dotazone.domain.heroes.prov.Item;


interface AdapterAction {
	fun initList(view: View?)
	fun initListItem(items: List<Item>)
	fun initListIHero(heroes: List<Hero>)
	fun initRating()
}
