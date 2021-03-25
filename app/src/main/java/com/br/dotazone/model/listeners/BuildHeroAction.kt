package com.br.dotazone.model.listeners

import com.br.dotazone.domain.heroes.prov.Item

interface BuildHeroAction {
	fun addingItemSlot(item: Item?)
	fun verifyPayment()
}
