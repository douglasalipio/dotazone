package br.com.dotazone.model.entity

import java.util.*


class Hero {
	var name: String? = null

	/**
	 * principal node hero *
	 */
	var idString: String = ""
	var bio: String = ""
	var atk: String = ""
	var roles: MutableList<String> = mutableListOf()
	var rolesL: MutableList<String> = mutableListOf()
	var skills: MutableList<Skill> = mutableListOf()
	var abilites: Ability? = null

	enum class HeroElementy {
		NAME, BIO, ATK, ATK_L, ROLES, ROLES_L
	}
}


