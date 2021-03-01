package com.br.dotazone.domain.heroes.prov


class Skill {
	private var dName: String? = null
	var affectes: String? = null
	var desc: String? = null
	var notes: String? = null
	var dmg: String? = null
	var attrib: String? = null
	var cmb: String? = null
	var lore: String? = null
	var hurl: String? = null
	var idString: String? = null

	fun getdName(): String? {
		return dName
	}

	fun setdName(dName: String?) {
		this.dName = dName
	}

	enum class SkillElementy {
		DNAME, AFFECTS, DESC, NOTES, DMG, ATTRIB, CMB, LORE, HURL
	}
}
