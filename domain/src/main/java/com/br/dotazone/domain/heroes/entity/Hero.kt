package com.br.dotazone.domain.heroes.entity

data class Hero(
		val name: String,
		val idString: String,
		val bio: String,
		val atk: String,
		val roles: List<String>
) {
	var heroSkills: MutableList<HeroSkill> = mutableListOf()
	var heroAbility: HeroAbility? = null
}
