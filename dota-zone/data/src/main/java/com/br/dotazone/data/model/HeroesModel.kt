package com.br.dotazone.data.model


import java.util.*

data class HeroModel(
		val name: String,
		val idString: String,
		val bio: String,
		val atk: String,
		val roles: List<String>
) {

	var skills: MutableList<HeroSkillModel> = mutableListOf()
	var ability: HeroAbilityModel? = null

	enum class Elements {
		Name, Bio, Atk, Roles;

		override fun toString(): String {
			return super.toString().toLowerCase(Locale.ROOT)
		}
	}

}

