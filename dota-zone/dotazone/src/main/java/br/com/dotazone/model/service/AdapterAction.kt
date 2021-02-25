package br.com.dotazone.model.service

import android.view.View
import br.com.dotazone.model.entity.Hero;
import br.com.dotazone.model.entity.Item;


interface AdapterAction {
	fun initList(view: View?)
	fun initListItem(items: List<Item>)
	fun initListIHero(heroes: List<Hero>)
	fun initRating()
}
