package com.br.dotazone.domain.heroes.entity

data class Hero(
		val name: String,
		val idString: String,
		val bio: String,
		val atk: String,
		val roles: List<String>
) {
	var skills: MutableList<Skill> = mutableListOf()
	var abilities: Ability? = null
}

data class Skill(
		val dName: String,
		val affectes: String,
		val desc: String,
		val notes: String,
		val dmg: String,
		val attrib: String,
		val cmb: String,
		val lore: String,
		val hurl: String,
		val idString: String
)

data class Ability(
		val name: String,
		val u: String,
		val pa: String,
		val str: MutableList<String>,
		val inte: MutableList<String>,
		val agi: MutableList<String>,
		val ms: String,
		val dmg: MutableList<String>,
		val armor: String,
		val dac: String,
		val droles: String,
		val idString: String
)
