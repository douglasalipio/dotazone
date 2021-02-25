package com.br.dotazone.model.service

import android.view.View
import com.br.dotazone.model.entity.Hero;
import com.br.dotazone.model.entity.Item;


interface AdapterAction {
	fun initList(view: View?)
	fun initListItem(items: List<Item>)
	fun initListIHero(heroes: List<Hero>)
	fun initRating()
}
