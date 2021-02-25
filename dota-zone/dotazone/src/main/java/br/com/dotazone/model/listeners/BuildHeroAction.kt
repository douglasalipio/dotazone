package br.com.dotazone.model.listeners

import br.com.dotazone.model.entity.Item

interface BuildHeroAction {
	fun addingItemSlot(item: Item?)
	fun verifyPayment()
}
