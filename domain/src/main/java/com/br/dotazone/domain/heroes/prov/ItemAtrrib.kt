package com.br.dotazone.domain.heroes.prov


class ItemAtrrib {
	var inteligence = 0
	var agility = 0
	var strength = 0
	var ms: String = ""
	var damage = 0
	var armor = 0
	var id: String = ""

	enum class ItemAttribElementy {
		ID, INTELIGENCE, AGILITY, STRENGTH, ARMOR, MS, DAMAGE
	}
}
