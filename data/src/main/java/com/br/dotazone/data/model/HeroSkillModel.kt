package com.br.dotazone.data.model

import java.util.*

data class HeroSkillModel(
		val dname: String,
		val affects: String,
		val desc: String,
		val notes: String,
		val dmg: String,
		val attrib: String,
		val cmb: String,
		val lore: String,
		val hurl: String
) {
	enum class Elements {
		Dname, Affects, Desc, Notes, Dmg, Attrib, Cmb, Lore, Hurl ;

		override fun toString(): String {
			return super.toString().toLowerCase(Locale.ROOT)
		}
	}
}
