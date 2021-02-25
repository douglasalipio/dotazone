package com.br.dotazone.model.listeners

import com.br.dotazone.model.entity.Item

interface BuildHeroAction {
	fun addingItemSlot(item: Item?)
	fun verifyPayment()
}
